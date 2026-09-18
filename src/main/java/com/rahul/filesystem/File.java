package com.rahul.filesystem;

import java.util.Scanner;

import com.rahul.virtualdisk.Disk;

class File {

    Disk disk = new Disk();
    byte[] bitmap = new byte[256];

    public void writeblock(int blockNumber, byte[] data) {
        long position = (long) blockNumber * 4096;
        disk.seek(position);
        disk.write(data);
    }

    public int findfreeblock() {
        for (int i = 2; i < 256; i++) {
            if (bitmap[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public void createfile(String filename) {

        int freeblock = findfreeblock();
        if (freeblock == -1) {
            System.out.println("disk is full");
            return;
        }
        byte[] data = filename.getBytes();
        bitmap[freeblock] = 1;
        writeblock(freeblock, data);
        writeblock(1, bitmap);
    }


    public void deletefile(String filename){
        for(int i = 2;i<256;i++){
            if(bitmap[i] == 1){
                byte[] data = disk.read((long) i * 4096);
                String bytesToName = new String(data, 0, 256).trim();
                if(bytesToName.equals(filename)){
                    bitmap[i] = 0;
                }
            }
        }

    }


    public void addText(String filename,String content){
        for(int i = 2;i<256;i++){
             if(bitmap[i] == 1){
                byte[] data = disk.read((long) i * 4096);
                String bytesToName = new String(data, 0, 256).trim();
                if(bytesToName.equals(filename)){
                byte[] contentData = content.getBytes();
                long contentPosition = (long) i * 4096 + 256;
                disk.seek(contentPosition);
                disk.write(contentData);
                System.out.println("written to " + filename);

                }
            }
        }
    }


    public String readText(String filename){
          for(int i = 2;i<256;i++){
             if(bitmap[i] == 1){
                byte[] data = disk.read((long) i * 4096);
                String bytesToName = new String(data, 0, 256).trim();
                if(bytesToName.equals(filename)){
                    String content = new String(data, 256, data.length - 256).trim();
                    return content;
                }
            }

          }

    }

    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);

        File fs = new File();

        fs.writeblock(1, fs.bitmap);

        while (true) {
            String cmd = scan.nextLine();
            String[] splitcmd = cmd.split(" ", 2);
            String filename = splitcmd[1];

            if ("cr".equals(splitcmd[0])) {
                System.out.println("creating file");
                fs.createfile(filename);

            } else if ("exit".equals(cmd)) {
                break;
            } else {
                System.out.println("");
            }

        }
    }
}
