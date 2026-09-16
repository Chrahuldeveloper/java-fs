# java fs

A simple file system built from scratch in Java to understand how file systems work internally.

## Current Features

- Virtual 1 MB disk using `disk.img`
- 4 KB blocks
- Block allocation using a bitmap
- Basic file creation

## Structure

```text
disk.img
├── Block 0 → File system metadata
├── Block 1 → Bitmap
└── Block 2+ → File data