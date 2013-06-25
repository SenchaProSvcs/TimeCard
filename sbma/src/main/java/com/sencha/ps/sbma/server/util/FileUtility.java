/*
 * Copyright (c) 2012. Sencha Inc.
 */
package com.sencha.ps.sbma.server.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class FileUtility {

  public enum ParentPathHandling {
    MUST_BE_PRESENT, CREATE_IF_NECESSARY
  }

  public static String compareTree(File sourceFile, File targetFile) {
    String[] sourceList = sourceFile.list();
    String[] targetList = targetFile.list();

    Arrays.sort(sourceList);
    Arrays.sort(targetList);

    if (sourceList.length < targetList.length) {
      return targetList[sourceList.length];
    }

    if (targetList.length < sourceList.length) {
      return sourceList[targetList.length];
    }

    for (int i = 0; i < sourceList.length; i++) {
      if (!sourceList[i].equals(targetList[i])) {
        return sourceList[i];
      }
      File sourceChildFile = new File(sourceFile, sourceList[i]);
      File targetChildFile = new File(targetFile, targetList[i]);
      if (sourceChildFile.isDirectory()) {
        if (!targetChildFile.isDirectory()) {
          return sourceList[i];
        }
        String childDifference = compareTree(sourceChildFile, targetChildFile);
        if (childDifference != null) {
          return childDifference;
        }
      } else {
        if (targetChildFile.isDirectory()) {
          return sourceList[i];
        }
        long childDifference = compareFile(sourceChildFile, targetChildFile);
        if (childDifference != -1) {
          return sourceList[i];
        }
      }
    }
    return null;
  }

  public static String compareTree(String sourcePath, String targetPath) {
    return compareTree(new File(sourcePath), new File(targetPath));
  }

  public static void copy(File sourceFile, File targetFile) {
    try {
      FileInputStream inputStream = new FileInputStream(sourceFile);
      try {
        FileOutputStream outputStream = new FileOutputStream(targetFile);
        try {
          FileChannel inputChannel = inputStream.getChannel();
          inputChannel.transferTo(0, inputChannel.size(), outputStream.getChannel());
        } finally {
          outputStream.close();
        }
      } finally {
        inputStream.close();
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void copyTree(File sourceDirectory, File targetDirectory) {
    copyTree(sourceDirectory, targetDirectory, ParentPathHandling.CREATE_IF_NECESSARY);
  }

  public static void copyTree(File sourceDirectory, File targetDirectory, ParentPathHandling parentPathHandling) {
    File[] files = sourceDirectory.listFiles();
    if (files == null) {
      throw new RuntimeException(new FileNotFoundException(sourceDirectory.getPath()));
    }
    switch (parentPathHandling) {
      case MUST_BE_PRESENT:
        targetDirectory.mkdir();
        break;
      case CREATE_IF_NECESSARY:
        targetDirectory.mkdirs();
        break;
    }
    if (!targetDirectory.isDirectory()) {
      throw new RuntimeException(new FileNotFoundException("Not a directory: " + targetDirectory.getPath()));
    }
    for (File oldFile : files) {
      File newFile = new File(targetDirectory, oldFile.getName());
      if (oldFile.isDirectory()) {
        copyTree(oldFile, newFile, parentPathHandling);
      } else {
        copy(oldFile, newFile);
      }
    }
  }

  public static boolean createDirectory(String name) {
    return new File(name).mkdirs();
  }

  public static File createRandomFile(String directoryName) {
    try {
      File file;
      File directoryFile = new File(directoryName);
      directoryFile.mkdirs();
      Random random = new Random();
      do {
        file = new File(directoryFile, Integer.toString(random.nextInt()));
      } while (!file.createNewFile());
      return file;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static void delete(File path) {
    if (path.isDirectory()) {
      File[] files = path.listFiles();
      for (File f : files) {
        delete(f);
      }
    }
    path.delete();
  }

  public static void delete(String path) {
    delete(new File(path));
  }

  public static void deleteOnExit(File path) {
    if (path.isDirectory()) {
      File[] files = path.listFiles();
      for (File f : files) {
        deleteOnExit(f);
      }
    }
    path.deleteOnExit();
  }

  public static void deleteOnExit(String path) {
    deleteOnExit(new File(path));
  }

  public static boolean ensurePathExists(File file) {
    return ensurePathExists(file, ParentPathHandling.CREATE_IF_NECESSARY);
  }

  public static boolean ensurePathExists(File file, ParentPathHandling parentPathHandling) {
    File parent = file.getParentFile();
    switch (parentPathHandling) {
      case MUST_BE_PRESENT:
        if (parent != null && !parent.exists()) {
          throw new RuntimeException(new FileNotFoundException(parent.getPath()));
        }
        return false;
      case CREATE_IF_NECESSARY:
        return parent.mkdirs();
      default:
        return false;
    }
  }

  public static boolean ensurePathExists(String path) {
    return ensurePathExists(new File(path));
  }

  public static boolean ensurePathExists(String path, boolean includeChild, ParentPathHandling parentPathHandling) {
    return ensurePathExists(new File(path), parentPathHandling);
  }

  public static boolean ensurePathExists(String path, ParentPathHandling parentPathHandling) {
    return ensurePathExists(new File(path), parentPathHandling);
  }

  public static String getCurrentDirectory() {
    try {
      return new File(".").getCanonicalPath();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String normalizePath(String path) {
    try {
      return new File(path).getCanonicalPath();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String readFile(File f) {
    return new String(readFileData(f));
  }

  public static String readFile(String path) {
    return readFile(new File(path));
  }

  public static byte[] readFileData(File file) {
    try {
      long length = file.length();
      if (length > Integer.MAX_VALUE) {
        throw new IllegalArgumentException("file is too large, file=" + file);
      }
      byte[] buffer = new byte[(int) length];
      InputStream inputStream = new FileInputStream(file);
      try {
        inputStream.read(buffer);
      } finally {
        inputStream.close();
      }
      return buffer;
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] readFileData(String path) {
    return readFileData(new File(path));
  }

  public static void unzip(File sourceZipFile, File targetDirectory) {
    try {
      targetDirectory.mkdirs();
      FileInputStream zipFileInputStream = new FileInputStream(sourceZipFile);
      ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(zipFileInputStream));
      try {
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
          String zipEntryName = zipEntry.getName();
          File zipEntryFile = new File(targetDirectory, zipEntryName);
          if (zipEntry.isDirectory()) {
            zipEntryFile.mkdir();
          } else {
            FileOutputStream fileOutputStream = new FileOutputStream(zipEntryFile);
            try {
              int readCount;
              byte[] data = new byte[32768];
              while ((readCount = zipInputStream.read(data, 0, data.length)) != -1) {
                fileOutputStream.write(data, 0, readCount);
              }
            } finally {
              fileOutputStream.close();
            }
          }
        }
      } finally {
        zipInputStream.close();
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void unzip(String sourceZipPath, String targetDirectoryPath) {
    unzip(new File(sourceZipPath), new File(targetDirectoryPath));
  }

  public static void writeFile(File file, String content) {
    writeFile(file, content, ParentPathHandling.CREATE_IF_NECESSARY);
  }

  public static void writeFile(File file, String content, ParentPathHandling parentPathHandling) {
    writeFileData(file, content.getBytes(), parentPathHandling);
  }

  public static void writeFile(String name, String content) {
    writeFile(new File(name), content);
  }

  public static void writeFileData(File file, byte[] data) {
    writeFileData(file, data, ParentPathHandling.CREATE_IF_NECESSARY);
  }

  public static void writeFileData(File file, byte[] data, ParentPathHandling parentPathHandling) {
    try {
      ensurePathExists(file, parentPathHandling);
      OutputStream targetStream = new FileOutputStream(file);
      try {
        targetStream.write(data);
      } finally {
        targetStream.close();
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void writeFileData(String fileName, byte[] data) {
    writeFileData(new File(fileName), data);
  }

  private static long compareFile(File sourceChildFile, File targetChildFile) {
    try {
      long sourceLength = sourceChildFile.length();
      long targetLength = targetChildFile.length();

      if (sourceLength < targetLength) {
        return sourceLength;
      }

      if (targetLength < sourceLength) {
        return targetLength;
      }

      BufferedInputStream sourceInputStream = new BufferedInputStream(new FileInputStream(sourceChildFile));
      try {
        BufferedInputStream targetInputStream = new BufferedInputStream(new FileInputStream(targetChildFile));
        try {
          for (int i = 0; i < sourceLength; i++) {
            int sourceValue = sourceInputStream.read();
            int targetValue = targetInputStream.read();
            if (sourceValue != targetValue) {
              return i;
            }
          }
          return -1;
        } finally {
          targetInputStream.close();
        }
      } finally {
        sourceInputStream.close();
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
