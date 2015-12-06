package com.liferay.docs.guestbook.model.impl;

import com.liferay.docs.guestbook.model.Guestbook;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Guestbook in entity cache.
 *
 * @author Diogo Peixoto
 * @see Guestbook
 * @generated
 */
public class GuestbookCacheModel implements CacheModel<Guestbook>,
    Externalizable {
    public String uuid;
    public long guestbookId;
    public long companyId;
    public long groupId;
    public long userId;
    public String userName;
    public long createDate;
    public long modifiedDate;
    public String name;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{uuid=");
        sb.append(uuid);
        sb.append(", guestbookId=");
        sb.append(guestbookId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", userName=");
        sb.append(userName);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", name=");
        sb.append(name);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Guestbook toEntityModel() {
        GuestbookImpl guestbookImpl = new GuestbookImpl();

        if (uuid == null) {
            guestbookImpl.setUuid(StringPool.BLANK);
        } else {
            guestbookImpl.setUuid(uuid);
        }

        guestbookImpl.setGuestbookId(guestbookId);
        guestbookImpl.setCompanyId(companyId);
        guestbookImpl.setGroupId(groupId);
        guestbookImpl.setUserId(userId);

        if (userName == null) {
            guestbookImpl.setUserName(StringPool.BLANK);
        } else {
            guestbookImpl.setUserName(userName);
        }

        if (createDate == Long.MIN_VALUE) {
            guestbookImpl.setCreateDate(null);
        } else {
            guestbookImpl.setCreateDate(new Date(createDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            guestbookImpl.setModifiedDate(null);
        } else {
            guestbookImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (name == null) {
            guestbookImpl.setName(StringPool.BLANK);
        } else {
            guestbookImpl.setName(name);
        }

        guestbookImpl.resetOriginalValues();

        return guestbookImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        uuid = objectInput.readUTF();
        guestbookId = objectInput.readLong();
        companyId = objectInput.readLong();
        groupId = objectInput.readLong();
        userId = objectInput.readLong();
        userName = objectInput.readUTF();
        createDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        name = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (uuid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uuid);
        }

        objectOutput.writeLong(guestbookId);
        objectOutput.writeLong(companyId);
        objectOutput.writeLong(groupId);
        objectOutput.writeLong(userId);

        if (userName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userName);
        }

        objectOutput.writeLong(createDate);
        objectOutput.writeLong(modifiedDate);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }
    }
}