
import com.example.practicaltestkonfio.dogList.data.local.dog.DogEntity
import com.example.practicaltestkonfio.dogList.data.remote.respond.DogDto
import com.example.practicaltestkonfio.dogList.domain.model.Dog

    fun DogDto.toDogEntity() : DogEntity {
        return DogEntity(
            age = age ?: 0,
            description = description ?: "",
            dogName = dogName ?: "",
            id = id ?: 0,
            imagePath = imagePath ?: ""
        )
    }

    fun DogEntity.toDog(): Dog {
        return Dog(
            age = age,
            description = description,
            dogName = dogName,
            id = id,
            imagePath = imagePath

        )

    }
