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

            if ("exit".equals(cmd)) {
                break;
            }

            String[] splitcmd = cmd.split(" ", 3);

            if ("cr".equals(splitcmd[0])) {

                if (splitcmd.length < 2) {
                    System.out.println("Usage: cr <filename>");
                    continue;
                }

                fs.createfile(splitcmd[1]);

            } else if ("write".equals(splitcmd[0])) {

                if (splitcmd.length < 3) {
                    System.out.println("Usage: write <filename> <content>");
                    continue;
                }

                fs.addText(splitcmd[1], splitcmd[2]);

            } else if ("read".equals(splitcmd[0])) {

                if (splitcmd.length < 2) {
                    System.out.println("Usage: read <filename>");
                    continue;
                }

                String content = fs.readText(splitcmd[1]);

                if (content != null) {
                    System.out.println(content);
                } else {
                    System.out.println("file not found");
                }

            } else if ("delete".equals(splitcmd[0])) {

                if (splitcmd.length < 2) {
                    System.out.println("Usage: delete <filename>");
                    continue;
                }

                fs.deletefile(splitcmd[1]);

            } else {

                System.out.println("unknown command");

            }
        }

        scan.close();
        fs.disk.close();
    }
}
