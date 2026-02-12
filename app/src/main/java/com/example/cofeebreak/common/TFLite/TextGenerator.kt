package com.example.cofeebreak.common.TFLite

import android.content.Context
import android.util.Log
import org.json.JSONObject
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.channels.FileChannel
import kotlin.math.exp
import java.util.Random

class TextGenerator(private val context: Context) {

    private val tokenizer: GPT2Tokenizer
    private val interpreter: Interpreter

    init {
        val (encoder, decoder) = loadVocab()
        val bpeRanks = loadMerges()
        val (byteEncoder, byteDecoder) = loadByteEncoder()

        tokenizer = GPT2Tokenizer(
            encoder,
            decoder,
            bpeRanks,
            byteEncoder,
            byteDecoder
        )

        val fd = context.assets.openFd("model.tflite")
        val inputStream = FileInputStream(fd.fileDescriptor)
        val channel = inputStream.channel
        val buffer = channel.map(FileChannel.MapMode.READ_ONLY, fd.startOffset, fd.declaredLength)

        interpreter = Interpreter(
            buffer,
            Interpreter.Options().setNumThreads(4)
        )
    }

//    fun generateText(prompt: String): String {
//        val tokens = tokenizer.encode(prompt).toMutableList()
//        val maxNewTokens = 30
//
//        repeat(maxNewTokens) {
//            val input = arrayOf(tokens.toIntArray())
//
//            interpreter.resizeInput(0, intArrayOf(1, tokens.size))
//            interpreter.allocateTensors()
//
//            // Получаем размер выходного тензора
//            val outputTensor = interpreter.getOutputTensor(0)
//            val outputShape = outputTensor.shape()  // например [1, 31, 50257]
//
//            val outputSeqLen = outputShape[1]  // длина последовательности
//            val output = Array(1) { Array(outputSeqLen) { FloatArray(50257) } }
//
//            interpreter.run(input, output)
//
//            val logits = output[0][outputSeqLen - 1]
//            val next = sample(logits, tokens)
//
//            if (next == 50256) return@repeat
//            tokens.add(next)
//        }
//
//        return tokenizer.decode(tokens)
//    }

    fun generateText(prompt: String): String {
        val inputIds = tokenizer.encode(prompt).toMutableList()
        val maxNewTokens = 30

        // 128 — безопасный размер для мобильной версии DistilGPT2
        val maxContextSize = 128

        for (i in 0 until maxNewTokens) {
            val currentInputIds = inputIds.toIntArray()
            val currentSeqLen = currentInputIds.size

            // 1. Обновляем размер входного тензора
            interpreter?.resizeInput(0, intArrayOf(1, currentSeqLen))
            interpreter?.allocateTensors()

            // 2. ВАЖНО: Вместо того чтобы гадать размер, спрашиваем его у самой модели
            val outputTensor = interpreter?.getOutputTensor(0)
            val outputShape = outputTensor?.shape() // Должен быть [1, currentSeqLen, 50257]

            // Проверка для отладки (можно убрать потом)
            if (outputShape != null && outputShape[1] != currentSeqLen) {
                Log.w("TFLite", "Внимание: модель ожидает ${outputShape[1]}, а у нас $currentSeqLen")
            }

            // Создаем буфер строго по форме, которую подтвердил интерпретатор
            val actualSeqLen = outputShape?.get(1) ?: currentSeqLen
            val outputBuffer = Array(1) { Array(actualSeqLen) { FloatArray(50257) } }

            try {
                // Запуск. Мы передаем вход как Array(1) { IntArray }
                interpreter?.run(arrayOf(currentInputIds), outputBuffer)

                // Берем логиты последнего слова.
                // Используем actualSeqLen - 1 на случай, если модель не изменила размер
                val lastTokenLogits = outputBuffer[0][actualSeqLen - 1]

                val nextTokenId = sampleWithTemperature(lastTokenLogits, inputIds, 0.8f, 1.2f)

                if (nextTokenId == 50256) break

                inputIds.add(nextTokenId)

                // Остановка на знаках препинания
                val decodedToken = tokenizer.decode(listOf(nextTokenId))
                if (i > 10 && (decodedToken.contains(".") || decodedToken.contains("\n"))) break

            } catch (e: Exception) {
                Log.e("TFLite", "Ошибка на итерации $i: ${e.message}")
                break
            }
        }

        val response = tokenizer.decode(inputIds)
        Log.e("generateText", response)
        return response
    }

    private fun sampleWithTemperature(
        logits: FloatArray,
        usedTokens: List<Int>,
        temperature: Float = 0.8f,
        repetitionPenalty: Float = 1.2f
    ): Int {
        val tempLogits = FloatArray(logits.size)

        for (i in logits.indices) {
            var logit = logits[i]

            // Если этот токен уже был использован, штрафуем его
            if (usedTokens.contains(i)) {
                // Если логит положительный, уменьшаем его (делим)
                // Если отрицательный — увеличиваем по модулю (умножаем)
                if (logit > 0) logit /= repetitionPenalty else logit *= repetitionPenalty
            }

            tempLogits[i] = logit / temperature
        }

        // Далее стандартный Softmax и Random выбор (как в предыдущем ответе)
        val maxLogit = tempLogits.maxOrNull() ?: 0f
        var sum = 0f
        val probs = FloatArray(tempLogits.size)
        for (i in tempLogits.indices) {
            probs[i] = kotlin.math.exp(tempLogits[i] - maxLogit).toFloat()
            sum += probs[i]
        }

        val random = java.util.Random().nextFloat()
        var cumulative = 0f
        for (i in probs.indices) {
            cumulative += probs[i] / sum
            if (random <= cumulative) return i
        }
        return tempLogits.indices.maxByOrNull { tempLogits[it] } ?: 0
    }


    // ---------- sampling ----------

//    private fun sample(logits: FloatArray, used: List<Int>): Int {
//        val temperature = 0.8f
//        val repetitionPenalty = 1.2f
//
//        val adjusted = FloatArray(logits.size)
//
//        for (i in logits.indices) {
//            var v = logits[i]
//            if (used.contains(i)) {
//                v = if (v > 0) v / repetitionPenalty else v * repetitionPenalty
//            }
//            adjusted[i] = v / temperature
//        }
//
//        val max = adjusted.maxOrNull() ?: 0f
//        val probs = FloatArray(adjusted.size)
//        var sum = 0f
//
//        for (i in adjusted.indices) {
//            probs[i] = exp(adjusted[i] - max)
//            sum += probs[i]
//        }
//
//        val r = Random().nextFloat()
//        var acc = 0f
//        for (i in probs.indices) {
//            acc += probs[i] / sum
//            if (r <= acc) return i
//        }
//
//        return probs.indices.maxByOrNull { probs[it] } ?: 0
//    }

    // ---------- loaders ----------

    private fun loadVocab(): Pair<Map<String, Int>, Map<Int, String>> {
        val json = context.assets.open("vocab.json").bufferedReader().readText()
        val obj = JSONObject(json)

        val encoder = mutableMapOf<String, Int>()
        val decoder = mutableMapOf<Int, String>()

        obj.keys().forEach {
            val id = obj.getInt(it)
            encoder[it] = id
            decoder[id] = it
        }
        return encoder to decoder
    }

    private fun loadMerges(): Map<Pair<String, String>, Int> {
        val lines = context.assets.open("merges.txt").bufferedReader().readLines()
        val map = mutableMapOf<Pair<String, String>, Int>()

        lines.drop(1).forEachIndexed { i, line ->
            val parts = line.split(" ")
            if (parts.size == 2) {
                map[parts[0] to parts[1]] = i
            }
        }
        return map
    }

    private fun loadByteEncoder(): Pair<Map<Int, String>, Map<String, Int>> {
        val json = context.assets.open("byte_encoder.json").bufferedReader().readText()
        val obj = JSONObject(json)

        val encoder = mutableMapOf<Int, String>()
        val decoder = mutableMapOf<String, Int>()

        obj.keys().forEach {
            val key = it.toInt()
            val value = obj.getString(it)
            encoder[key] = value
            decoder[value] = key
        }
        return encoder to decoder
    }
}

