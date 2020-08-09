package cn.edu.scujcc.workthreeweek.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : xxx@xx
 *     time   : 2020/08/09
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * @author Administrator
 */
public class Student implements Parcelable {
    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
    public String mName;
    public String mSex;

    public Student(String name, String sex) {
        this.mName = name;
        this.mSex = sex;
    }

    protected Student(Parcel in) {
        mName = in.readString();
        mSex = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mSex);
    }
}

