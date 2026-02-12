package com.example.cofeebreak.common.TFLite

class GPT2Tokenizer(
    private val encoder: Map<String, Int>,
    private val decoder: Map<Int, String>,
    private val bpeRanks: Map<Pair<String, String>, Int>,
    private val byteEncoder: Map<Int, String>,
    private val byteDecoder: Map<String, Int>
) {

    private val regex = Regex(
        """'s|'t|'re|'ve|'m|'ll|'d| ?\p{L}+| ?\p{N}+| ?[^\s\p{L}\p{N}]+|\s+(?!\S)|\s+"""
    )

    fun encode(text: String): MutableList<Int> {
        val tokens = regex.findAll(text).map { match ->
            match.value
                .toByteArray(Charsets.UTF_8)
                .map { byteEncoder[it.toInt() and 0xFF]!! }
                .joinToString("")
        }

        return tokens
            .map { bpe(it) }
            .flatten()
            .map { encoder[it]!! }
            .toMutableList()
    }

    fun decode(tokens: List<Int>): String {
        val text = tokens.joinToString("") { decoder[it] ?: "" }

        val bytes = text.map {
            byteDecoder[it.toString()]!!.toByte()
        }.toByteArray()

        return bytes.toString(Charsets.UTF_8)
    }

    private fun bpe(token: String): List<String> {
        if (token.length <= 1) return listOf(token)

        var word = token.map { it.toString() }

        while (true) {
            val pairs = getPairs(word)
            val rankedPairs = pairs.filter { bpeRanks.containsKey(it) }

            if (rankedPairs.isEmpty()) {
                break  // безопасно, обычный while
            }

            // находим пару с минимальным рангом
            val best = rankedPairs.minByOrNull { bpeRanks[it]!! } ?: break
            val (first, second) = best

            val newWord = mutableListOf<String>()
            var i = 0

            while (i < word.size) {
                if (i < word.size - 1 && word[i] == first && word[i + 1] == second) {
                    newWord.add(first + second)
                    i += 2
                } else {
                    newWord.add(word[i])
                    i++
                }
            }

            word = newWord
            if (word.size == 1) break
        }

        return word
    }


    private fun getPairs(word: List<String>): Set<Pair<String, String>> {
        val pairs = mutableSetOf<Pair<String, String>>()
        for (i in 0 until word.size - 1) {
            pairs.add(word[i] to word[i + 1])
        }
        return pairs
    }
}

