package ExternalSort;

// PS D:\UCA\RiteshSir\ExternalSort> java -cp .. ExternalSort.GenerateTestData
import java.util.*;
import java.io.*;

public class sort {
  public void sort(File input, File output) throws IOException {
    long fileSize = input.length();
    long freeRam = Runtime.getRuntime().freeMemory() / 2;

    freeRam = 80;

    int blockSize = (int) freeRam;

    if(freeRam < blockSize) {
      int fileCnt = (int) (fileSize / freeRam) + 1;
      if(fileCnt > 1024) {
        throw new RuntimeException("Please inc RAM!");
      }

      blockSize = fileCnt;
    }

    System.out.println("BlockSize: " + blockSize);

    List<File> tempFiles = createSortdTempFiles(input, blockSize);
    System.out.println("No. of temp files created: " + tempFiles.size());

    mergeFiles(output, tempFiles);
  }

  private List<File> createSortdTempFiles(File input, int maxBlockSize) throws IOException {
    List<File> tempFiles = new ArrayList<>();
    List<String> data = new ArrayList<>();

    int currBlockSize = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(input))) {
      String s = br.readLine();

      while (s != null) {
        data.add(s);

        currBlockSize += s.length();

        if (currBlockSize > maxBlockSize) {
          File f = createTempFile(data);
          data.clear();
          currBlockSize = 0;
          tempFiles.add(f);
        }
        s = br.readLine();
      }

      if (!data.isEmpty()) {
        File f = createTempFile(data);
        tempFiles.add(f);
      }
    }
    return tempFiles;
  }

  private File createTempFile(List<String> data) throws IOException {
    Collections.sort(data);

    File f = new File(UUID.randomUUID() + ".txt");
    f.deleteOnExit();
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
      for (String d : data) {
        bw.write(d);
        bw.newLine();
      }
    }
    return f;
  }

  private void mergeFiles(File output, List<File> tempFiles) throws IOException {
    PriorityQueue<CustomBufferReader> pq = new PriorityQueue<>();

    for(File t : tempFiles) {
      pq.add(new CustomBufferReader(t));
    }

    try(BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
      while(!pq.isEmpty()) {
        CustomBufferReader customBR = pq.poll();
        bw.write(customBR.pop());
        bw.newLine();
        if(customBR.peek() != null){
          pq.add(customBR);
        }
      }
    }
  }

}
