package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.Order
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.repository.OrderRepository
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class OrderRepositoryImpl: OrderRepository {
    override suspend fun getMyOrder(id: Profile): List<Order> {
        return supabase.postgrest["order"].select(
            columns = Columns.list(
                "id",
                "name",
                "options",
                "price",
                "coffee_image",
                "count"
            )
        ){
            filter {
                and {
                    eq("user_id", id.user_id)
                    eq("status", "current")
                }
            }
        }.decodeList<Order>()
    }

    override suspend fun getCurrentOrder(id: Profile): List<Order> {
        return supabase.postgrest["order"].select(
            columns = Columns.list(
                "name",
                "created_at",
                "time_to",
                "price",
                "coffee_image"
            )
        ){
            filter {
                and {
                    eq("user_id", id.user_id)
                    eq("status", "current")
                }
            }
        }.decodeList<Order>()
    }

    override suspend fun getHistoryOrder(id: Profile): List<Order> {
        return supabase.postgrest["order"].select(
            columns = Columns.list(
                "name",
                "created_at",
                "time_to",
                "price",
                "coffee_image"
            )
        ){
            filter {
                and {
                    eq("user_id", id.user_id)
                    eq("status", "history")
                }
            }
        }.decodeList<Order>()
    }
}