package br.com.renato.androidloadjar;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView viewById = (TextView) findViewById(R.id.id_text);
        File file = saveFile();
        viewById.setText("" + loadLibrary(file));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private File saveFile() {
        try {
            File file = new File(getFilesDir(), "/br/com/renato/androidloadjar/CompareTest.apk");
            file.getParentFile().mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String s = "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";
            fileOutputStream.write(Base64.decode(s, Base64.DEFAULT));
            return file;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



    public Integer loadLibrary(File jar) {
        try {
            dalvik.system.PathClassLoader classLoader =  (dalvik.system.PathClassLoader) ClassLoader.getSystemClassLoader();



            System.out.println(jar);

            java.net.URL url = jar.toURI().toURL();

            System.out.println(url);

//            PathClassLoader loader = new PathClassLoader(jar.getPath(), classLoader);


            DexClassLoader loader = new DexClassLoader(jar.getAbsolutePath(), this.getDir("outdex", Context.MODE_PRIVATE).getAbsolutePath(), null, this.getClassLoader());

//            URLClassLoader loader = new URLClassLoader(new URL[]{}, classLoader);

//            java.lang.reflect.Method method = java.net.URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{java.net.URL.class});
//            method.setAccessible(true); /*promote the method to public access*/
//            method.invoke(loader, new Object[]{url});

            Class<?> aClass = loader.loadClass("br.com.renato.androidloadjar.Compare");
            Comparable o = (Comparable) aClass.newInstance();
            return (Integer) o.compareTo("");
        } catch ( java.net.MalformedURLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
