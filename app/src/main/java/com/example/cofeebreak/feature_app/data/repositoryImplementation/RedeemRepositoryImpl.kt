package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.Redeem
import com.example.cofeebreak.feature_app.domain.repository.RedeemRepository
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import javax.inject.Inject

class RedeemRepositoryImpl @Inject constructor(): RedeemRepository {
    override suspend fun getRedeem(): List<Redeem> {
        return supabase.postgrest["redeem"].select(
            columns = Columns.list(
                "id",
                "name",
                "really_up_to",
                "price",
                "coffee_image"
            )
        ).decodeList<Redeem>()
    }
}