package com.platzi.feature.catshome.data.moshi

import com.squareup.moshi.*
import java.lang.reflect.Type

/**
 * [DefaultIfNullFactory] extends from [JsonAdapter.Factory] and it is used when a property from
 * the response is `null`. If a property is null Moshi will set the default value specified.
 */
class DefaultIfNullFactory : JsonAdapter.Factory {

    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        if (!Types.getRawType(type).isAnnotationPresent(DefaultIfNull::class.java)) {
            return null
        }

        val delegate = moshi.nextAdapter<Any>(this, type, annotations)

        return object : JsonAdapter<Any>() {
            override fun fromJson(reader: JsonReader): Any? {
                val blob = reader.readJsonValue() as Map<*, *>
                val nonNulls = blob.filterValues { it != null }.toMutableMap()

                return delegate.fromJsonValue(nonNulls)
            }

            override fun toJson(writer: JsonWriter, value: Any?) {
                return delegate.toJson(writer, value)
            }
        }
    }
}