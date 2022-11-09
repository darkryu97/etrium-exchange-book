package br.com.sekai.book_exchange.mapper


import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper

object DozerMapper {
    private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()

    fun <O, D> parserObject(origin: O, destination: Class<D>?): D {
        return mapper.map(origin, destination)
    }

    fun <O, D> parserListObject(origin: List<O>, destination: Class<D>?): ArrayList<D> {
        val destinationObject: ArrayList<D> = ArrayList()
        for(o in origin){
            destinationObject.add(mapper.map(origin,destination))
        }
        return destinationObject
    }
}
