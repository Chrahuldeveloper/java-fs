# Mini File System

A small file system built from scratch in Java to understand how operating systems store and manage files internally.

The project uses a normal file called `disk.img` as a **virtual disk**. Instead of creating real Linux files, the Java program manages bytes and blocks inside `disk.img`.

---

## How It Works

The basic architecture is:

```text
                    MiniFS CLI
                       │
                       ▼
                  FileSystem
                       │
             ┌─────────┼─────────┐
             ▼         ▼         ▼
          create      write     read
             │         │         │
             └─────────┼─────────┘
                       ▼
                    Disk.java
                       │
                       ▼
                RandomAccessFile
                       │
                       ▼
                    disk.img
                       │
                       ▼
              Ubuntu File System
                       │
                       ▼
                      SSD