package com.github.badoualy.telegram.tl.api;

import com.github.badoualy.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.github.badoualy.telegram.tl.StreamUtils.readInt;
import static com.github.badoualy.telegram.tl.StreamUtils.readLong;
import static com.github.badoualy.telegram.tl.StreamUtils.readTLString;
import static com.github.badoualy.telegram.tl.StreamUtils.writeInt;
import static com.github.badoualy.telegram.tl.StreamUtils.writeLong;
import static com.github.badoualy.telegram.tl.StreamUtils.writeString;
import static com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_CONSTRUCTOR_ID;
import static com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_INT32;
import static com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_INT64;
import static com.github.badoualy.telegram.tl.TLObjectUtils.computeTLStringSerializedSize;

/**
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
public class TLChannelForbidden extends TLAbsChat {

    public static final int CONSTRUCTOR_ID = 0x17d493d5;

    protected int flags;

    protected boolean broadcast;
    protected boolean megagroup;
    protected long accessHash;
    protected String title;
    protected Integer untilDate;

    private final String _constructor = "channelForbidden#17d493d5";

    public TLChannelForbidden() {
    }

    public TLChannelForbidden(boolean broadcast, boolean megagroup, long id, long accessHash, String title,
                              Integer untilDate) {
        this.broadcast = broadcast;
        this.megagroup = megagroup;
        this.id = id;
        this.accessHash = accessHash;
        this.title = title;
        this.untilDate = untilDate;
    }

    private void computeFlags() {
        flags = 0;
        flags = broadcast ? (flags | 32) : (flags & ~32);
        flags = megagroup ? (flags | 256) : (flags & ~256);
        flags = untilDate != null ? (flags | 65536) : (flags & ~65536);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        computeFlags();

        writeInt(flags, stream);
        writeLong(id, stream);
        writeLong(accessHash, stream);
        writeString(title, stream);
        if ((flags & 65536) != 0) {
            if (untilDate == null) {
                throwNullFieldException("untilDate", flags);
            }
            writeInt(untilDate, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = readInt(stream);
        broadcast = (flags & 32) != 0;
        megagroup = (flags & 256) != 0;
        id = readLong(stream);
        accessHash = readLong(stream);
        title = readTLString(stream);
        untilDate = (flags & 65536) != 0 ? readInt(stream) : null;
    }

    @Override
    public int computeSerializedSize() {
        computeFlags();

        int size = SIZE_CONSTRUCTOR_ID;
        size += SIZE_INT32;
        size += SIZE_INT64;
        size += SIZE_INT64;
        size += computeTLStringSerializedSize(title);
        if ((flags & 65536) != 0) {
            if (untilDate == null) {
                throwNullFieldException("untilDate", flags);
            }
            size += SIZE_INT32;
        }
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

    public boolean getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    public boolean getMegagroup() {
        return megagroup;
    }

    public void setMegagroup(boolean megagroup) {
        this.megagroup = megagroup;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(Integer untilDate) {
        this.untilDate = untilDate;
    }
}
