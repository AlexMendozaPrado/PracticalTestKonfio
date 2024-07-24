package com.example.practicaltestkonfio.dogList.data.Repository

import com.example.practicaltestkonfio.dogList.data.local.dog.DogDatabase
import com.example.practicaltestkonfio.dogList.data.local.dog.DogEntity
import com.example.practicaltestkonfio.dogList.data.remote.DogApi
import com.example.practicaltestkonfio.dogList.domain.Repository.DogListRepository
import com.example.practicaltestkonfio.dogList.domain.model.Dog
import com.example.practicaltestkonfio.dogList.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import toDog
import toDogEntity
import java.io.IOException


class DogListRepositoryImpl @Inject constructor(
    private val dogApi: DogApi,
    private val dogDatabase: DogDatabase
) : DogListRepository {

    override suspend fun getDogsList():Flow<Resource<List<Dog>>> {

        return flow {
            emit(Resource.Loading(true))

            val localDogList = dogDatabase.dogDao.getAllDogs()

            val shouldLoadLocalDogList = localDogList.isNotEmpty()

            if (shouldLoadLocalDogList) {
                emit(
                    Resource.Success(data =
                    localDogList.map { dogEntity ->
                        dogEntity.toDog()
                    })
                )

                emit(Resource.Loading(false))
                return@flow

            }

            val remoteDogsList = try {
                dogApi.getDogsList()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "No se carga la info"))
                return@flow

            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "No se carga la info"))
                return@flow

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "No se carga la info"))
                return@flow

            }
            val dogEntities = remoteDogsList.dogs.let{
                it.map { dogDto -> dogDto.toDogEntity() }
            }

            dogDatabase.dogDao.upsertDogList(dogEntities)

            emit(Resource.Success(dogEntities.map { it.toDog() }))
            emit(Resource.Loading(false))


        }


    }


}

