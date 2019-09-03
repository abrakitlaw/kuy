# Add project specific ProGuard rules here.
#---------------------------------basic code----------------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
#-dontwarn
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# --------------------------------------------------------------------
# STRIP all Log messages
# --------------------------------------------------------------------
-assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** d(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}

#---------------------------------COMMON ANDROID LIBS----------------------------------
# Dagger ProGuard rules.
# https://github.com/square/dagger
-dontwarn dagger.internal.codegen.**

-keepclassmembers class dagger.* { *; }
-keepclassmembers class javax.inject.* { *; }
-keepclassmembers class * extends dagger.internal.Binding
-keepclassmembers class * extends dagger.internal.ModuleAdapter
-keepclassmembers class * extends dagger.internal.StaticInjection

# butterknife rules
-keepclassmembers public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }
-keepclassmembers class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

#As the experts said, there is no need to bofuscate RXJAVA 2.x.

#zxing need to be optimize
#-keep class com.google.zxing.**{*;}

-keepclassmembers class de.hdodenhof.circleimageview.**{*;}

### Glide, Glide Okttp Module, Glide Transformations
-keepclassmembers public class * implements com.bumptech.glide.module.GlideModule
-keepclassmembers public class * extends com.bumptech.glide.module.AppGlideModule
-keepclassmembers public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keepclassmembers class com.caverock.androidsvg.**{*;}
-keepclassmembers class com.jakewharton.rxbinding2.**{*;}
-keepclassmembers class com.wajahatkarim3.easyflipview.**{*;}
-keepclassmembers class com.zhuinden.simplestack.**{*;}

#-keep class com.google.auto.value.**{*;}
-keepclassmembers class net.cachapa.expandablelayout.**{*;}
-keepclassmembers class me.grantland.widget.**{*;}
-keepclassmembers class com.synnapps.carouselview.**{*;}
-keepclassmembers class io.michaelrocks.libphonenumber.android.**{*;}
-keepclassmembers class com.f2prateek.rx.preferences2.**{*;}
-keepclassmembers class com.afollestad.materialdialogs.**{*;}

-keepclassmembers class org.greenrobot.eventbus.**{*;}
-keepclassmembers class io.hypertrack.smart_scheduler.**{*;}

# lifecycle
-keepclassmembers class * {
    @android.arch.lifecycle.OnLifecycleEvent *;
}

# In order to receive deobfuscated crash reports
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception