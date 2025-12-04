# Add project specific ProGuard rules here.
# Keep Parcelable classes
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep data models
-keep class com.example.gamestore.models.** { *; }
