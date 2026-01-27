package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.Order
import com.example.cofeebreak.feature_app.domain.repository.OrderRepository
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class OrderRepositoryImpl: OrderRepository {
    override suspend fun getMyOrder(): List<Order> {
        return supabase.postgrest["order"].select(
            columns = Columns.list(
                "id",
                "name",
                "options",
                "price",
                "coffee_image",
                "count"
            )
        ).decodeList<Order>()
    }
}