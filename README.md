# Mini File System

A simple file system built from scratch in Java to understand how disks, blocks, bytes, and file storage work internally.

The project uses a normal file called `disk.img` as a **virtual disk**.

---

## 1. Virtual Disk

The virtual disk is:

- Size: **1 MB**
- Total bytes: **1,048,576 bytes**
- Block size: **4096 bytes (4 KB)**
- Total blocks: **256**

Calculation:

```text
1,048,576 / 4,096 = 256 blocks

# Mini File System

A simple file system built from scratch in Java to understand how disks, blocks, bytes, and file storage work internally.

The project uses a normal file called `disk.img` as a **virtual disk**.

---

## 1. Virtual Disk

The virtual disk is:

- Size: **1 MB**
- Total bytes: **1,048,576 bytes**
- Block size: **4096 bytes (4 KB)**
- Total blocks: **256**

Calculation:

```text
1,048,576 / 4,096 = 256 blocks


┌────────────────────────────────────┐
│ Block 0                            │
│ Reserved                           │
├────────────────────────────────────┤
│ Block 1                            │
│ Bitmap                             │
├────────────────────────────────────┤
│ Block 2                            │
│ File / Data                        │
├────────────────────────────────────┤
│ Block 3                            │
│ File / Data                        │
├────────────────────────────────────┤
│ Block 4                            │
│ File / Data                        │
├────────────────────────────────────┤
│                ...                 │
├────────────────────────────────────┤
│ Block 255                          │
│ File / Data                        │
└────────────────────────────────────┘