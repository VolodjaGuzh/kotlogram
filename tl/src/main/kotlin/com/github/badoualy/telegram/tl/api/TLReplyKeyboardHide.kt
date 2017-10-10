package com.github.badoualy.telegram.tl.api

import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_CONSTRUCTOR_ID
import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_INT32
import com.github.badoualy.telegram.tl.serialization.TLDeserializer
import com.github.badoualy.telegram.tl.serialization.TLSerializer
import java.io.IOException

/**
 * replyKeyboardHide#a03e5b85
 *
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
class TLReplyKeyboardHide() : TLAbsReplyMarkup() {
    @Transient
    var selective: Boolean = false

    private val _constructor: String = "replyKeyboardHide#a03e5b85"

    override val constructorId: Int = CONSTRUCTOR_ID

    constructor(selective: Boolean) : this() {
        this.selective = selective
    }

    protected override fun computeFlags() {
        _flags = 0
        updateFlags(selective, 4)
    }

    @Throws(IOException::class)
    override fun serializeBody(tlSerializer: TLSerializer) = with (tlSerializer)  {
        computeFlags()

        writeInt(_flags)
    }

    @Throws(IOException::class)
    override fun deserializeBody(tlDeserializer: TLDeserializer) = with (tlDeserializer)  {
        _flags = readInt()
        selective = isMask(4)
    }

    override fun computeSerializedSize(): Int {
        computeFlags()

        var size = SIZE_CONSTRUCTOR_ID
        size += SIZE_INT32
        return size
    }

    override fun toString() = _constructor

    override fun equals(other: Any?): Boolean {
        if (other !is TLReplyKeyboardHide) return false
        if (other === this) return true

        return _flags == other._flags
                && selective == other.selective
    }
    companion object  {
        const val CONSTRUCTOR_ID: Int = 0xa03e5b85.toInt()
    }
}