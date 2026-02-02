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
                    eq("status", "not paid")
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

    override suspend fun saveOrder(order: Order): Order {
        val newOrder = Order(name = order.name,
            time_to = order.time_to,
            price = order.price,
            coffee_image = order.coffee_image,
            options = "single | iced | medium | full ice",
            count = order.count,
            user_id = order.user_id,
            status = order.status,
            ristretto = order.ristretto,
            volume = order.volume,
            barista = order.barista,
            coffee_type = order.coffee_type,
            milk = order.milk,
            syrup = order.syrup,
            additives = order.additives
            )
        return supabase.postgrest["order"].insert(newOrder){
            select(
                columns = Columns.list(
                    "id"
                )
            )
        }.decodeSingle<Order>()
    }

    override suspend fun getOrderById(id: Order): Order {
        return supabase.postgrest["order"].select(){
            filter {
                and {
                    eq("id", id.id)
                    eq("user_id", id.user_id)
                }
            }
        }.decodeSingle<Order>()
    }
}