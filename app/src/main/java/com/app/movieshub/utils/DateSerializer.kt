package com.app.movieshub.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.*


@Serializer(forClass = DateSerializer::class)
object DateSerializer : KSerializer<Date> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("DateSerializer", PrimitiveKind.STRING)

    override fun serialize(output: Encoder, obj: Date) {
        SimpleDateFormat("yyyy-mm-dd").equals(output.encodeString(obj.time.toString()))
    }

    override fun deserialize(input: Decoder): Date {
        return SimpleDateFormat("yyyy-mm-dd").parse(input.decodeString())
    }
}