package com.github.badoualy.telegram.tl.api.request;

import com.github.badoualy.telegram.tl.TLContext;
import com.github.badoualy.telegram.tl.api.TLInputPhoneContact;
import com.github.badoualy.telegram.tl.api.contacts.TLImportedContacts;
import com.github.badoualy.telegram.tl.core.TLMethod;
import com.github.badoualy.telegram.tl.core.TLObject;
import com.github.badoualy.telegram.tl.core.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.github.badoualy.telegram.tl.StreamUtils.readTLObject;
import static com.github.badoualy.telegram.tl.StreamUtils.readTLVector;
import static com.github.badoualy.telegram.tl.StreamUtils.writeTLVector;
import static com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_CONSTRUCTOR_ID;

/**
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
public class TLRequestContactsImportContacts extends TLMethod<TLImportedContacts> {

    public static final int CONSTRUCTOR_ID = 0x2c800be5;

    protected TLVector<TLInputPhoneContact> contacts;

    private final String _constructor = "contacts.importContacts#2c800be5";

    public TLRequestContactsImportContacts() {
    }

    public TLRequestContactsImportContacts(TLVector<TLInputPhoneContact> contacts, boolean replace) {
        this.contacts = contacts;
    }

    @Override
    public TLImportedContacts deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject response = readTLObject(stream, context);
        if (response == null) {
            throw new IOException("Unable to parse response");
        }
        if (!(response instanceof TLImportedContacts)) {
            throw new IOException(
                    "Incorrect response type, expected " + getClass().getCanonicalName() + ", found " + response
                            .getClass().getCanonicalName());
        }
        return (TLImportedContacts) response;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeTLVector(contacts, stream);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        contacts = readTLVector(stream, context);
    }

    @Override
    public int computeSerializedSize() {
        int size = SIZE_CONSTRUCTOR_ID;
        size += contacts.computeSerializedSize();
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

    public TLVector<TLInputPhoneContact> getContacts() {
        return contacts;
    }

    public void setContacts(TLVector<TLInputPhoneContact> contacts) {
        this.contacts = contacts;
    }

}
