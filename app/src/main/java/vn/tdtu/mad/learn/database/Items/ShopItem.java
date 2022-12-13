package vn.tdtu.mad.learn.database.Items;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Shop_Items")
public class ShopItem {
    @PrimaryKey(autoGenerate = true)
    public int mID;

    @NonNull
    @ColumnInfo(name = "Name")
    public String mName;

    @ColumnInfo(name = "Amount")
    public double mAmount;

    @NonNull
    @ColumnInfo(name = "Offer")
    public String mOffer;

    @ColumnInfo(name = "ShopType")
    public ShopTypes mShopType;

    @ColumnInfo(name = "Redeemed")
    public boolean mRedeemed;

    public ShopItem(@NonNull String mName, double mAmount, @NonNull String mOffer, ShopTypes shopType,boolean mRedeemed) {
        this.mName = mName;
        this.mAmount = mAmount;
        this.mOffer = mOffer;
        this.mShopType = shopType;
        this.mRedeemed = mRedeemed;
    }

    public int getmID() {
        return mID;
    }
    public void setmID(int mID) {
        this.mID = mID;
    }

    @NonNull
    public String getmName() {
        return mName;
    }
    public void setmName(@NonNull String mName) {
        this.mName = mName;
    }

    public double getmAmount() {
        return mAmount;
    }
    public void setmAmount(double mAmount) {
        this.mAmount = mAmount;
    }

    @NonNull
    public String getmOffer() {
        return mOffer;
    }
    public void setmOffer(@NonNull String mOffer) {
        this.mOffer = mOffer;
    }

    public ShopTypes getmShopType() {
        return mShopType;
    }

    public void setmShopType(ShopTypes mShopType) {
        this.mShopType = mShopType;
    }

    public boolean ismRedeemed() {
        return mRedeemed;
    }
    public void setmRedeemed(boolean mRedeemed) {
        this.mRedeemed = mRedeemed;
    }
}
