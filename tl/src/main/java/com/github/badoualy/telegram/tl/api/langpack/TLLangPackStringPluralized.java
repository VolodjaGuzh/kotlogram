package com.github.badoualy.telegram.tl.api.langpack;

import com.github.badoualy.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.github.badoualy.telegram.tl.StreamUtils.readInt;
import static com.github.badoualy.telegram.tl.StreamUtils.readTLString;
import static com.github.badoualy.telegram.tl.StreamUtils.writeInt;
import static com.github.badoualy.telegram.tl.StreamUtils.writeString;
import static com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_CONSTRUCTOR_ID;
import static com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_INT32;
import static com.github.badoualy.telegram.tl.TLObjectUtils.computeTLStringSerializedSize;

public class TLLangPackStringPluralized extends TLAbsLangPackString {

    public static final int CONSTRUCTOR_ID = 0x6c47ac9f;

    protected int flags;
    protected String zeroValue;
    protected String oneValue;
    protected String twoValue;
    protected String fewValue;
    protected String manyValue;
    protected String otherValue;

    private final String _constructor = "langPackStringPluralized#6c47ac9f";

    public TLLangPackStringPluralized() {
    }

    public TLLangPackStringPluralized(String key, String zeroValue, String oneValue, String twoValue, String fewValue,
                                      String manyValue, String otherValue) {
        super(key);
        this.zeroValue = zeroValue;
        this.oneValue = oneValue;
        this.twoValue = twoValue;
        this.fewValue = fewValue;
        this.manyValue = manyValue;
        this.otherValue = otherValue;
    }

    private void computeFlags() {
        flags = 0;
        flags = zeroValue != null ? (flags | 1) : (flags & ~1);
        flags = oneValue != null ? (flags | 2) : (flags & ~2);
        flags = twoValue != null ? (flags | 4) : (flags & ~4);
        flags = fewValue != null ? (flags | 8) : (flags & ~8);
        flags = manyValue != null ? (flags | 16) : (flags & ~16);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        computeFlags();
        writeInt(flags, stream);
        writeString(key, stream);
        if ((flags & 1) != 0) {
            if (zeroValue == null) {
                throwNullFieldException("zeroValue", flags);
            }
            writeString(zeroValue, stream);
        }
        if ((flags & 2) != 0) {
            if (oneValue == null) {
                throwNullFieldException("oneValue", flags);
            }
            writeString(oneValue, stream);
        }
        if ((flags & 4) != 0) {
            if (twoValue == null) {
                throwNullFieldException("twoValue", flags);
            }
            writeString(twoValue, stream);
        }
        if ((flags & 8) != 0) {
            if (fewValue == null) {
                throwNullFieldException("fewValue", flags);
            }
            writeString(fewValue, stream);
        }
        if ((flags & 16) != 0) {
            if (manyValue == null) {
                throwNullFieldException("manyValue", flags);
            }
            writeString(manyValue, stream);
        }
        writeString(otherValue, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = readInt(stream);
        key = readTLString(stream);
        zeroValue = (flags & 1) != 0 ? readTLString(stream) : null;
        oneValue = (flags & 2) != 0 ? readTLString(stream) : null;
        twoValue = (flags & 4) != 0 ? readTLString(stream) : null;
        fewValue = (flags & 8) != 0 ? readTLString(stream) : null;
        manyValue = (flags & 16) != 0 ? readTLString(stream) : null;
        otherValue = readTLString(stream);
    }

    @Override
    public int computeSerializedSize() {
        computeFlags();
        int size = SIZE_CONSTRUCTOR_ID;
        size += SIZE_INT32;
        size += computeTLStringSerializedSize(key);
        if ((flags & 1) != 0) {
            if (zeroValue == null) {
                throwNullFieldException("zeroValue", flags);
            }
            size += computeTLStringSerializedSize(zeroValue);
        }
        if ((flags & 2) != 0) {
            if (oneValue == null) {
                throwNullFieldException("oneValue", flags);
            }
            size += computeTLStringSerializedSize(oneValue);
        }
        if ((flags & 4) != 0) {
            if (twoValue == null) {
                throwNullFieldException("twoValue", flags);
            }
            size += computeTLStringSerializedSize(twoValue);
        }
        if ((flags & 8) != 0) {
            if (fewValue == null) {
                throwNullFieldException("fewValue", flags);
            }
            size += computeTLStringSerializedSize(fewValue);
        }
        if ((flags & 16) != 0) {
            if (manyValue == null) {
                throwNullFieldException("manyValue", flags);
            }
            size += computeTLStringSerializedSize(manyValue);
        }
        size += computeTLStringSerializedSize(otherValue);
        return size;
    }

    @Override
    public String toString() {
        return _constructor;
    }

    @Override
    public int getConstructorId() {
        return CONSTRUCTOR_ID;
    }

    public String getZeroValue() {
        return zeroValue;
    }

    public void setZeroValue(String zeroValue) {
        this.zeroValue = zeroValue;
    }

    public String getOneValue() {
        return oneValue;
    }

    public void setOneValue(String oneValue) {
        this.oneValue = oneValue;
    }

    public String getTwoValue() {
        return twoValue;
    }

    public void setTwoValue(String twoValue) {
        this.twoValue = twoValue;
    }

    public String getFewValue() {
        return fewValue;
    }

    public void setFewValue(String fewValue) {
        this.fewValue = fewValue;
    }

    public String getManyValue() {
        return manyValue;
    }

    public void setManyValue(String manyValue) {
        this.manyValue = manyValue;
    }

    public String getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }
}
