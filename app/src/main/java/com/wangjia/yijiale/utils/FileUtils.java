package com.wangjia.yijiale.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.wangjia.yijiale.YiApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Title:文件工具类
 */
public class FileUtils {

    private static FileUtils instance;
    // 文件缓存路径
    private static final String CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "xiaomi" + File.separator;

    //下载目录
    private File downloadDir;
    //缓存目录
    private File cacheDir;
    //图片缓存目录
    private static File cacheImageDir;
    //语音文件缓存目录
    private File cacheAudioDir;
    //视频文件缓存目录
    private File cacheVideoDir;
    //错误日志目录
    private File errorDir;
    /**
     * 文件保存文件夹路径
     */
    public final static String FILE_PAHT = "/xiaomi";

    public static FileUtils getInstance() {
        if (instance == null) {
            synchronized (FileUtils.class) {
                if (instance == null) {
                    instance = new FileUtils(YiApplication.getInstance());
                }
            }
        }
        return instance;
    }

    private FileUtils() {

    }

    public static String getPath(Context context, Uri uri) {

        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private FileUtils(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(CACHE_DIR, "/cache");
        } else {
            cacheDir = context.getCacheDir();
        }
        if (!cacheDir.exists())
            cacheDir.mkdirs();
        cacheImageDir = new File(CACHE_DIR, "/image/");
        if (!cacheImageDir.exists())
            cacheImageDir.mkdirs();
        downloadDir = new File(CACHE_DIR, "/download/");
        if (!downloadDir.exists())
            downloadDir.mkdirs();
        cacheAudioDir = new File(CACHE_DIR, "/audio/");
        if (!cacheAudioDir.exists())
            cacheAudioDir.mkdirs();
        errorDir = new File(CACHE_DIR, "/error/");
        if (!errorDir.exists())
            errorDir.mkdirs();
        cacheVideoDir = new File(CACHE_DIR, "/video/");
        if (!cacheVideoDir.exists())
            cacheVideoDir.mkdirs();
    }

    /**
     * 创建根目录
     *
     * @param path 目录路径
     */
    public static void createDirFile(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 创建文件
     *
     * @param path 文件路径
     * @return 创建的文件
     */
    public static File createNewFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return null;
            }
        }
        return file;
    }

    /**
     * 获取缓存目录
     *
     * @return
     * @Description:
     */
    public File getDownloadDir() {
        return downloadDir;
    }

    /**
     * 获取缓存目录
     *
     * @return
     * @Description:
     */
    public File getCacheDir() {
        return cacheDir;
    }

    /**
     * 获取错误日志目录
     *
     * @return
     * @Description:
     */
    public File getErrorDir() {
        return errorDir;
    }


    /**
     * 创建一个临时图片文件
     *
     * @return
     * @Description:
     */
    public File newTempImageFile() {
        File file = new File(cacheImageDir, System.currentTimeMillis() + ".jpg");
        return file;
    }

    public File getCacheAudioDir() {
        return cacheAudioDir;
    }

    public void setCacheAudioDir(File cacheAudioDir) {
        this.cacheAudioDir = cacheAudioDir;
    }

    /**
     * 创建一个临时语音文件
     *
     * @return
     * @Description:
     */
    public File newTempAudioFile() {
        return new File(cacheAudioDir, System.currentTimeMillis() + ".wav");
    }

    /**
     * 创建一个临时语音文件
     *
     * @return
     * @Description:
     */
    public File newTempVideoFile() {
        return new File(cacheVideoDir, System.currentTimeMillis() + ".mp4");
    }

    /**
     * 判断是否安装SD卡
     *
     * @return
     */
    public static boolean checkSaveLocationExists() {
        String sDCardStatus = Environment.getExternalStorageState();
        boolean status;
        if (sDCardStatus.equals(Environment.MEDIA_MOUNTED)) {
            status = true;
        } else
            status = false;
        return status;
    }

    /**
     * 删除目录(包括：目录里的所有文件)
     *
     * @param fileName
     * @return
     */
    public static boolean deleteDirectory(String fileName) {
        boolean status;
        SecurityManager checker = new SecurityManager();

        if (!fileName.equals("")) {

            File path = Environment.getExternalStorageDirectory();
            File newPath = new File(path.toString() + fileName);
            checker.checkDelete(newPath.toString());
            if (newPath.isDirectory()) {
                String[] list_file = newPath.list();
                // delete all files within the specified directory and then
                // delete the directory
                try {
                    for (int i = 0; i < list_file.length; i++) {
                        File deletedFile = new File(newPath.toString() + "/" + list_file[i].toString());
                        deletedFile.delete();
                    }
                    newPath.delete();

                    status = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    status = false;
                }

            } else
                status = false;
        } else
            status = false;
        return status;
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName) {
        boolean status;
        SecurityManager checker = new SecurityManager();

        if (!fileName.equals("")) {

            File path = Environment.getExternalStorageDirectory();
            File newPath = new File(path.toString() + fileName);
            checker.checkDelete(newPath.toString());
            if (newPath.isFile()) {
                try {

                    newPath.delete();
                    status = true;
                } catch (SecurityException se) {
                    se.printStackTrace();
                    status = false;
                }
            } else
                status = false;
        } else
            status = false;
        return status;
    }

//    /**
//     * 清除临时存放的图片文件
//     *
//     * @param file_path_list
//     * @return
//     */
/*    public static void deleteFileFromPath(Context context, List<?> file_path_list) {

        try {
            if (file_path_list != null && file_path_list.size() > 0) {
                if (file_path_list.get(0) instanceof String) {
                    for (int i = 0; i < file_path_list.size(); i++) {
                        File newPath = new File((String) file_path_list.get(i));
                        if (newPath.exists()) {
                            try {
                                if (newPath.delete()) {
                                } else {
                                }
                            } catch (SecurityException se) {
                                se.printStackTrace();
                            }
                            // 最后通知图库更新
                            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(newPath.getAbsoluteFile())));
                        }
                    }
                } else if (file_path_list.get(0) instanceof File) {
                    for (int i = 0; i < file_path_list.size(); i++) {
                        File newPath = new File(((File) file_path_list.get(i)).getPath());
                        if (((File) file_path_list.get(i)).exists()) {
                            try {
                                if (((File) file_path_list.get(i)).delete()) {
                                } else {
                                }
                            } catch (SecurityException se) {
                                se.printStackTrace();
                            }
                            // 最后通知图库更新
                            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(newPath.getAbsoluteFile())));
                        }
                    }

                }
                AppContext.getTemp_file_path_list().clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static final String getTempFilePath(Context context) {

        return context.getCacheDir() + File.separator + "temp";

    }

    public File getDownloadFilePath() {

        return downloadDir;

    }

    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir.getPath();
    }

    /**
     * 将图片保存到指定的路径
     *
     * @param filePath
     * @param name
     * @param bitmap
     * @return
     */
    public static String savePic(String filePath, String name, Bitmap bitmap) {
        File file = new File(filePath);
        if (!file.exists())
            file.mkdirs();

        File f = new File(filePath + File.separator + name);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getPath();
    }

    /**
     * 将图片保存到指定的路径
     *
     * @param filePath
     * @param bitmap
     * @return
     */
    public static String savePic(Context context, String filePath, Bitmap bitmap) {
        String sys_file_path = null;
        try {
            sys_file_path = "";

            File f = new File(filePath);

            f.createNewFile();

            FileOutputStream fOut = null;

            fOut = new FileOutputStream(f);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);

            fOut.flush();


            fOut.close();

            // 其次把文件插入到系统图库
            String fileName = System.currentTimeMillis() + ".jpg";

            String bitPath = MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    bitmap, fileName, null);
            Uri uuUri = Uri.parse(bitPath);
            if (!StringFunction.isNotNull(uuUri)) {
                return "";
            }
            sys_file_path = getPath(context, uuUri);

            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(f.getAbsoluteFile())));
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
                System.gc();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return f.getPath();
//        //存放临时照片路径，上传成功后 ，好删除！
//        AppContext.getTemp_file_path_list().add(filePath);
//        AppContext.getTemp_file_path_list().add(sys_file_path);
        return sys_file_path;
    }



    /**
     * 获取文件路径
     */
    public static String getSDPathForFileName(String name) {
        String savePathString = null;
        try {
            String savePAth = Environment.getExternalStorageDirectory()
                    + FILE_PAHT;
            File file1 = new File(savePAth);
            if (!file1.exists()) {
                file1.mkdir();
            }
            savePathString = Environment.getExternalStorageDirectory()
                    + FILE_PAHT + "/" + name;
            File file2 = new File(savePathString);
            if (file2.exists()) {
                file2.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return savePathString;
    }


    /**
     * 将一个inputstream里面的数据写入SD卡中 第一个参数为目录名 第二个参数为输入流
     */
    public static File write2SDFromInput(File dowloadpath, InputStream inputstream) {
        OutputStream output = null;
        try {
//            file = createSDFile(path);
            output = new FileOutputStream(dowloadpath);
            // 4k为单位，每4K写一次
            byte buffer[] = new byte[4 * 1024];
            int temp = 0;
            while ((temp = inputstream.read(buffer)) != -1) {
                // 获取指定信,防止写入没用的信息
                output.write(buffer, 0, temp);
            }
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dowloadpath;
    }

}