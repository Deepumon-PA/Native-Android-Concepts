//
// Created by 91756 on 09-09-2022.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_deepu_z_learn_securingkeysUsingND_HideKeyUsingNDKActivity_stringFromJNI(JNIEnv *env, jobject object) {
    std::string hello = "Hello from Jni";
    return env->NewStringUTF(hello.c_str());
}