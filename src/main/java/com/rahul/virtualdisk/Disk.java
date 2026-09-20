package com.rahul.virtualdisk;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Disk {

    public RandomAccessFile disk;

    public Disk() {

        try {
            disk = new RandomAccessFile("disk.img", "rw");
            disk.setLength(1024 * 1024);

            System.out.println("created disk");

        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public void seek(long position) {

        try {
            disk.seek(position);

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void write(byte[] data) {

        try {
            disk.write(data);

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void close(byte[] data) {

        try {
            disk.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }


    public byte[] read(long position){
        byte[] data = new byte[4096];

        try {
            disk.seek(position);
            disk.read(data);

        } catch (IOException e) {
            System.err.println(e);
        }

        return data;

    }

}
