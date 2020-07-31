/**
 * Consider a text file(i.e., a file with a .txt extension) where each line contains a single log record with the following columns, space-separated in the
 * following order:
 *   1. The hostname of the host that made the request.
 *   2. this next column's values are missing and described by a hyphen(i.e., -).
 *   3. This next column's values are missing and described by a hyphen(i.e., -).
 *   4. A timestamp enclosed in square brackets following the format [DD/mmm/YYYY:HH:MM:SS -0400], where DD is the day of the month, mmm is the name
 *      of the month, YYYY is the year, HH:MM:SS is the time in 24-hour format, and -0400 is the time zone,
 *   5. The request, enclosed in quotes(e.g., "GET/images/NASA-logosmall.gif HTTP/1.0").
 *   6. The HTTP response code.
 *   7. The total number of bytes sent in their response:
 *
 * For example, given the following log record:
 *              unicomp6.unicomp.net - - [01/Jul/1995:00:00:06 -0400] "GET/shuttle/countdown/HTTP/1.0" 200 3985
 *
 * Given a string, filename, that denotes the name of a real text file that has a .txt extension, create a file named bytes_filename where filename is
 * the file name string, to store information about large responses. the created file must contain two lines:
 *   1. The first line must contain the number of requests that have more than 5000 bytes sent in their response.
 *   2. The second line must contain the total sum of bytes sent by all responses sending more than 5000 bytes.
 *
 * For example, there are 10 responses, 3 of which have Bytes = 6000. The filename = 'input1.txt'. Create the file bytes_input1.txt and store '3\n18000\n'
 * to the file. In that string, '\n' is the newline charater.
 *
 * Note: The output file has to be written to the current directory.
 */
import java.nio.file.*;
import java.util.*;
public class LargeResponses {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String arg[]) throws Exception{
        //read the string filename
        String filename;
        filename = scan.nextLine();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        long bytes = 0, count = 0;
        for(String line: lines){
            line = line.trim();
            String words[] = line.split("\\s");/**white space*/  /**"\\W" non-word*/
            if(Long.parseLong(words[words.length-1]) > 50001){
                count ++;
                bytes += Long.parseLong(words[words.length-1]);
            }
        }
        Files.write(Paths.get("bytes_" + filename), (count+"\n" + bytes +"\n").getBytes());
    }
}
