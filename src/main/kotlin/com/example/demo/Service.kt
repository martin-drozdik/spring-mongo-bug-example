package com.example.demo

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

data class Person (val id: String)

@Component
class PersonHandler(val template: ReactiveMongoTemplate)
{
    fun initialize()
    {
        println("Initializing")

        val jim: Mono<Person> =  template.save(Person("Jim"))
        val john: Mono<Person> = template.save(Person("John"))
        val jack: Mono<Person> = template.save(Person("Jack"))

        launch(jim)
        launch(john)
        launch(jack)

        println("Finished Initializing")
    }

    fun launch(mono: Mono<Person>)
    {
      //  mono.subscribe({println(it.id)}, {println("Error")}) // This works
         mono.block()//  This just hangs
    }
}