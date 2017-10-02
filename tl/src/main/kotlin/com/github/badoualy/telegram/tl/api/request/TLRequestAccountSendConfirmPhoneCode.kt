package com.github.badoualy.telegram.tl.api.request

import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_BOOLEAN
import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_CONSTRUCTOR_ID
import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_INT32
import com.github.badoualy.telegram.tl.TLObjectUtils.computeTLStringSerializedSize
import com.github.badoualy.telegram.tl.api.auth.TLSentCode
import com.github.badoualy.telegram.tl.core.TLMethod
import com.github.badoualy.telegram.tl.serialization.TLDeserializer
import com.github.badoualy.telegram.tl.serialization.TLSerializer
import java.io.IOException

/**
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
class TLRequestAccountSendConfirmPhoneCode() : TLMethod<TLSentCode>() {
    @Transient
    var allowFlashcall: Boolean = false

    var hash: String = ""

    var currentNumber: Boolean? = null

    private val _constructor: String = "account.sendConfirmPhoneCode#1516d7bd"

    override val constructorId: Int = CONSTRUCTOR_ID

    constructor(
            allowFlashcall: Boolean,
            hash: String,
            currentNumber: Boolean?
    ) : this() {
        this.allowFlashcall = allowFlashcall
        this.hash = hash
        this.currentNumber = currentNumber
    }

    @Throws(IOException::class)
    override fun deserializeResponse(tlDeserializer: TLDeserializer): TLSentCode = tlDeserializer.readTLObject(TLSentCode::class, TLSentCode.CONSTRUCTOR_ID)

    protected override fun computeFlags() {
        _flags = 0
        updateFlags(allowFlashcall, 1)
        // If field is not serialized force it to false
        if (currentNumber != null && !isMask(1)) currentNumber = null
    }

    @Throws(IOException::class)
    override fun serializeBody(tlSerializer: TLSerializer) = with (tlSerializer)  {
        computeFlags()

        writeInt(_flags)
        writeString(hash)
        doIfMask(currentNumber, 1) { writeBoolean(it) }
    }

    @Throws(IOException::class)
    override fun deserializeBody(tlDeserializer: TLDeserializer) = with (tlDeserializer)  {
        _flags = readInt()
        allowFlashcall = isMask(1)
        hash = readString()
        currentNumber = readIfMask(1) { readBoolean() }
    }

    override fun computeSerializedSize(): Int {
        computeFlags()

        var size = SIZE_CONSTRUCTOR_ID
        size += SIZE_INT32
        size += computeTLStringSerializedSize(hash)
        size += getIntIfMask(currentNumber, 1) { SIZE_BOOLEAN }
        return size
    }

    override fun toString() = _constructor

    override fun equals(other: Any?): Boolean {
        if (other !is TLRequestAccountSendConfirmPhoneCode) return false
        if (other === this) return true

        return _flags == other._flags
                && allowFlashcall == other.allowFlashcall
                && hash == other.hash
                && currentNumber == other.currentNumber
    }
    companion object  {
        const val CONSTRUCTOR_ID: Int = 0x1516d7bd.toInt()
    }
}
