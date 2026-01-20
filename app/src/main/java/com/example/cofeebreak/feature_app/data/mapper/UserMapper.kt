package com.example.cofeebreak.feature_app.data.mapper
//
//import com.example.cofeebreak.feature_app.data.dto.UserDto
//import com.example.cofeebreak.feature_app.domain.model.User
//import javax.inject.Inject
//
//class UserMapper @Inject constructor() {
//    fun toDomain(dto: UserDto): User {
//        return User(
//            id = dto.id,
//            email = dto.email,
//            password = dto.password
//        )
//    }
//
//    fun toDto(domain: User): UserDto {
//        return UserDto(
//            id = domain.id,
//            email = domain.email,
//            password = domain.password
//        )
//    }
//}