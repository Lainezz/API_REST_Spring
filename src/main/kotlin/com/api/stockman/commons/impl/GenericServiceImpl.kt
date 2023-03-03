package com.api.stockman.commons.impl

import com.api.stockman.commons.api.GenericServiceAPI
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.io.Serializable


@Service
/**
 * Implementación de la interfaz genérica [GenericServiceAPI]
 * @see GenericServiceAPI
 */
abstract class GenericServiceImpl<T, ID: Serializable> : GenericServiceAPI<T,ID> {


    /**
     * Inserta 1 registro en la tabla
     * @param entity: Entidad de tipo [T] a insertar
     * @return [entity] entidad insertada
     */
    override fun insertOne(entity: T): T {
        return dao.save(entity)
    }

    /**
     * Elimina 1 registro de la tabla
     * @param id: id de la entidad a buscar para su eliminacióon
     */
    override fun deleteOne(id: ID) {
        dao.deleteById(id)
    }

    /**
     * Obtiene 1 registro de la tabla
     * @param id: id de la entidad a buscar
     * @return [obj]: objeto de tipo [T] con el registro de la tabla de la BDD
     */
    override operator fun get(id: ID): T? {
        val obj = dao.findById(id)
        if(obj.isPresent){
            return obj.get()
        }
        return null
    }

    /**
     * Obtiene todos los registros de la tabla
     * @return [returnList]: MutableList de elementos [T]
     */
    override val all: MutableList<T>?
        get(){
            val returnList: MutableList<T> = mutableListOf()
            dao.findAll().forEach( { entry:T -> returnList.add(entry) } )
            return returnList
        }


    /**
     * Atributo de clase que contiene los métodos de Spring para el acceso a datos.
     * Se especificará en las clases que implementen esta clase abstracta
     * @see CrudRepository
     */
    abstract val dao: CrudRepository<T, ID>
}