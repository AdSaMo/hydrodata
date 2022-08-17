package com.logocito.atlas.data

@Target(AnnotationTarget.PROPERTY)
@Retention (AnnotationRetention.RUNTIME)
annotation class Campo(
    val descripcion : String,
    val sección : String ,
    val posición : Int,
    val imagen : Int = -1,
)

@Target(AnnotationTarget.PROPERTY)
@Retention (AnnotationRetention.RUNTIME)
annotation class Opcion(
    val descripcion : String,
)


