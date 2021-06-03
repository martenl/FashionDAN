package de.martenl.fashiondan.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "de.martenl.fashiondan.admin.repository")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    static int reverseAndToInt(final String s) {
        int result = 0;
        for (int i = 0, factor = 1; i < s.length(); i++, factor *= 10) {
            final int currentInt = Character.getNumericValue(s.charAt(i));
            result += currentInt * factor;
        }
        return result;
    }

    static void zerosToTheEnd(int[] arr) {
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0 && arr[j] == 0) {
                swap(arr, i, j);
            }

            if (arr[j] != 0) {
                j++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<int[]> queries) {
        final Map<Integer, Integer> frequencies = new HashMap<>();
        final List<Integer> result = new ArrayList<>();
        for (int[] query : queries) {
            final int queryType = query[0];
            final int element = query[1];
            if (queryType == 1) {
                insertElement(frequencies, element);
            }
            if (queryType == 2) {
                deleteElement(frequencies, element);
            }
            if (queryType == 3) {
                result.add(freqMatch(frequencies, element));
            }
        }
        return result;
    }

    static void insertElement(final Map<Integer, Integer> frequencies, final int element) {
        if (frequencies.containsKey(element)) {
            frequencies.put(element, frequencies.get(element) + 1);
        } else {
            frequencies.put(element, 1);
        }
    }

    static void deleteElement(final Map<Integer, Integer> frequencies, final int element) {
        if (!frequencies.containsKey(element)) {
            return;
        }
        final int currentFreq = frequencies.get(element);
        if (currentFreq > 1) {
            frequencies.put(element, frequencies.get(element) + 1);
        } else {
            frequencies.remove(element);
        }
    }

    static int freqMatch(final Map<Integer, Integer> frequencies, final int requiredFreq) {
        if (frequencies.containsValue(requiredFreq)) {
            return 1;
        }
        return 0;
    }

    public static int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length == 1) {
            if (A[0] < 0) return 1;
            return A[0] + 1;
        }

        Arrays.sort(A);
        if (A[0] > 1) return 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < 0 || A[i - 1] == A[i]) continue;
            if (A[i - 1] <= 0 && A[i] != 1) return 1;
            if (A[i - 1] + 1 != A[i]) return A[i - 1] + 1;
            if (i == A.length - 1) return A[i] + 1;

        }
        return 1;
    }

    static List<String> readFromFile(String path) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        List<String> result = new ArrayList<>(629);
        String line = reader.readLine();
        while (line != null) {
            result.add(line);
            line = reader.readLine();
        }
        return result;

    }

    public static int lengthOfLongestSubstring(String s) {
        final int offset = 'a';
        boolean[] occurence = new boolean[26];
        Map<Character, Integer> occurences = new HashMap<>();
        int longestSubstringLength = 0;
        int currentSubstringLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (occurences.containsKey(c)) {
                int lastOccurence = occurences.get(c);
                if (lastOccurence > i - currentSubstringLength) {
                    if (longestSubstringLength < currentSubstringLength) {
                        longestSubstringLength = currentSubstringLength;
                    }
                    currentSubstringLength = i - lastOccurence;
                }
            } else {

                currentSubstringLength++;
            }
            occurences.put(c, i);
            /*if(occurence[c-offset]) {
                System.out.println(c);
                Arrays.fill(occurence, false);
                if( longestSubstringLength < currentSubstringLength) {
                    longestSubstringLength = currentSubstringLength;
                }
                currentSubstringLength = 0;
            } else {
                occurence[c-offset] = true;
                currentSubstringLength++;
            }*/
        }
        if (longestSubstringLength < currentSubstringLength) {
            longestSubstringLength = currentSubstringLength;
        }
        return longestSubstringLength;
    }

    static String urlify(char[] original) {
        final char[] reversedUrlSpace = new char[]{'0', '2', '%'};
        int i = original.length - 1, j = original.length - 1;
        while (original[i] == ' ') {
            i--;
        }
        while (i > 0) {
            if (original[i] == ' ') {
                for (char c : reversedUrlSpace) {
                    original[j--] = c;
                }
            } else {
                original[j] = original[i];
                j--;
            }
            i--;
        }
        return new String(original);
    }

    static boolean hasAllUniqueCharacters(final String s) {
        if (s.length() < 26) return false;

        final char[] characters = s.toCharArray();
        Arrays.sort(characters);

        if (characters[0] != 'a' || characters[characters.length - 1] != 'z') return false;

        for (int i = 1; i < characters.length; i++) {
            if (characters[i] != characters[i - 1] && characters[i] != characters[i - 1] + 1) return false;
        }

        return true;
    }

    static boolean edits(String firstString, String secondString) {
        if (firstString.equals(secondString)) return true;
        if (Math.abs(firstString.length() - secondString.length()) > 1) return false;

        if (firstString.length() == secondString.length()) return replacedCharacter(firstString, secondString);

        return firstString.length() > secondString.length()
                ? removedCharacter(firstString, secondString) : removedCharacter(firstString, secondString);
    }

    private static boolean removedCharacter(String firstString, String secondString) {
        boolean characterAlreadyRemoved = false;
        int j = 0;
        for (int i = 0; i < firstString.length() - 1; i++) {
            if (firstString.charAt(i) == secondString.charAt(j)) {
                j++;
                continue;
            }
            if (characterAlreadyRemoved) return false;
            characterAlreadyRemoved = true;
        }
        return true;
    }

    static boolean isRotation(String s1, String s2) {
        return (s2 + s1 + s2).contains(s2);
    }

    private static boolean replacedCharacter(String firstString, String secondString) {
        boolean characterAlreadyReplaced = false;
        for (int i = 0; i < firstString.length(); i++) {
            if (firstString.charAt(i) == secondString.charAt(i)) continue;
            if (characterAlreadyReplaced) return false;
            characterAlreadyReplaced = true;
        }
        return true;
    }

    public static void bane(String[] args) throws IOException {

        System.out.println(2 << 1);
        System.out.println(6 << 1);
        System.out.println(maxNotPresent(new int[]{2, 3, 3}, new int[]{2, 3, 1}));
        System.out.println(maxNotPresent(new int[]{1, 2, 6, 3}, new int[]{1, 3, 4, 3}));
        System.out.println(maxNotPresent(new int[]{4, 2, 1, 6, 5}, new int[]{3, 2, 1, 4, 7}));
        /*SetOfStacks<Integer> setOfStacks = new SetOfStacks<>();
        for (int i = 0; i < 10; i++) setOfStacks.push(i);
        for (int i = 0; i < 3; i++) setOfStacks.pop();
        setOfStacks.printMetrics();*/
        //System.out.println(isRotation("System.out.println", "em.out.printlnSyst"));
        //System.out.println(isRotation("System.out.println", "em.ou.printlnSyst"));
       /* Random rnd = new Random();
        int[] arr = new int[100];
        for(int i = 0;i<100;i++) {
            arr[i] = rnd.nextInt(40);
        }
        int[] result = twoSum(arr, 77);
        if(result[0] != -1) {
            System.out.println(arr[result[0]]);
            System.out.println(arr[result[1]]);
        }

        pale, ple -> true
pales, pale -> true
pale, bale -> true
pale, bake -> false
        */
        //System.out.println(edits("pale", "ple"));
        //System.out.println(edits("pales", "pale"));
        //System.out.println(edits("pale", "bale"));
        //System.out.println(edits("pale", "bake"));
        /*List<String> answers = readFromFile("c:\\test\\answers.txt");
        List<String> brackets = readFromFile("c:\\test\\brackets.txt");

        for(int i = 0;i<brackets.size();i++) {
            final String bracket = brackets.get(i);
            final String answer = answers.get(i);
            final String myAnswer = isBalanced(bracket);
            if(!answer.equals(myAnswer)) {
                System.out.println(bracket);
            }

        }

        var triplets = readTriplets("c:\\test\\triplets.txt");

        int[] a = triplets.get(0);//new int[]{1, 3, 5, 7}; // 1 + 1 +1 +3
        int[] b = triplets.get(1);//new int[]{5, 7, 9};
        int[] c = triplets.get(2);//new int[]{7, 9, 11, 13};
        System.out.println(triplets(a, b, c));*/
        //System.out.println(solution(new int[]{102, -9, 0, 101}));

        /*int[] arr = new int[]{1, 3, 5, 7};
        updateArray(arr, 0, 5);
        for (int i : arr) {
            System.out.println(i);
        }*/
        //System.out.println(mergeSortAndCount(new int[]{7 ,5, 3, 1},0,3));
        //for (int i = 0; i < 100; i++) System.out.println(fizzbuzzValue(i));
		/*System.out.println(reverseAndToInt("1001"));
		System.out.println(reverseAndToInt("2345"));
		System.out.println(reverseAndToInt("1234567890"));
		System.out.println(reverseAndToInt("68008"));
		int arrLength = 100000;
        int[] arr = new int[arrLength];
        Random rnd = new Random();
        for(int i = 0;i<arrLength;i++) if (i%8 == 0) arr[i] = 0; else arr[i] = rnd.nextInt();
		zerosToTheEnd(arr);
        for(int elem : arr) System.out.println(elem);*/
    }

    static String isBalanced(String s) {
        if (s.length() % 2 != 0) return "NO";
        final Stack<Character> bracketStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            final char currentBracket = s.charAt(i);
            if (currentBracket == '('
                    || currentBracket == '['
                    || currentBracket == '{') {
                bracketStack.push(currentBracket);
                continue;
            }
            if (bracketStack.empty()) {
                return "NO";
            }
            final Character bracketFromStack = bracketStack.pop();
            if (currentBracket == ')' && bracketFromStack != '('
                    || currentBracket == ']' && bracketFromStack != '['
                    || currentBracket == '}' && bracketFromStack != '{') {
                return "NO";
            }
        }
        return bracketStack.empty() ? "YES" : "NO";
    }

    static void updateArray(int[] arr, int newValue, int oldValue) {
        if (newValue == oldValue) return;

        int position = Arrays.binarySearch(arr, oldValue);
        if (newValue > oldValue) {
            for (int i = position; i < arr.length; i++) {
                if (i + 1 == arr.length || arr[i + 1] > newValue) {
                    arr[i] = newValue;
                    return;
                }
                arr[i] = arr[i + 1];
            }
        } else {
            for (int i = position; i >= 0; i--) {
                if (i == 0 || arr[i - 1] <= newValue) {
                    arr[i] = newValue;
                    return;
                }
                arr[i] = arr[i - 1];
            }
        }
    }

    static long triplets(int[] a, int[] b, int[] c) {
        // 145333908482693
        // 12603652660415
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        long triples = 0;
        int j = 0;
        long uniqueJs = 0;
        int k = 0;
        long uniqueKs = 0;
        System.out.println((long) a.length * (long) b.length * (long) c.length);
        for (int i = 0; i < b.length; i++) {
            if (i > 0 && b[i] == b[i - 1]) continue;
            while (j < a.length && a[j] <= b[i]) {
                if (j == 0 || a[j] != a[j - 1]) uniqueJs++;
                j++;
            }
            while (k < c.length && c[k] <= b[i]) {
                if (k == 0 || c[k] != c[k - 1]) uniqueKs++;
                k++;
            }
            triples += uniqueJs * uniqueKs;
        }
        return triples;
    }

    static void merge(int[] arr, int l, int r) {

    }

    static int[] twoSum(int[] arr, int target) {
        Map<Integer, List<Integer>> numbers = new HashMap<>();
        int[] result = new int[]{-1, -1};
        for (int i = 0; i < arr.length; i++) {
            if (numbers.containsKey(arr[i])) {
                numbers.get(arr[i]).add(i);
            } else {
                List<Integer> lst = new ArrayList<>();
                lst.add(i);
                numbers.put(arr[i], lst);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int missingNumber = target - arr[i];
            if (numbers.containsKey(missingNumber)) {
                result[0] = i;
                result[1] = numbers.get(missingNumber).get(0);
            }
        }
        return result;
    }

    static List<int[]> readTriplets(String path) throws IOException {
        var triplets = new ArrayList<int[]>();
        var reader = new BufferedReader(new FileReader(new File(path)));
        String line = reader.readLine();
        while (line != null) {
            var intList = Stream.of(line.split(" ")).filter(x -> !x.isBlank()).map(Integer::parseInt).collect(toList());
            int[] arr = new int[intList.size()];
            for (int i = 0; i < intList.size(); i++) {
                arr[i] = intList.get(i);
            }
            triplets.add(arr);
            line = reader.readLine();
        }
        return triplets;
    }

    static int longestBitIsland(final int n) {
        final String binaryInteger = Integer.toBinaryString(n);
        System.out.println(binaryInteger);
        boolean inBitIsland = false;
        int longestBitIslandLength = 0;
        int currentBitIslandLength = 0;
        for (int i = 0; i < binaryInteger.length(); i++) {
            if (binaryInteger.charAt(i) == '1') {
                if (inBitIsland && currentBitIslandLength == 0) continue;

                if (inBitIsland) {
                    inBitIsland = false;
                    if (currentBitIslandLength > longestBitIslandLength) {
                        longestBitIslandLength = currentBitIslandLength;
                    }
                    currentBitIslandLength = 0;
                } else {
                    inBitIsland = true;
                }
            } else {
                if (inBitIsland) {
                    currentBitIslandLength++;
                }
            }
        }
        return longestBitIslandLength;
    }

    public static void pain(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            Pattern p = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                int[] query = new int[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Integer.parseInt(m.group(1));
                    query[1] = Integer.parseInt(m.group(2));
                    queries.add(query);
                }
            }
            List<Integer> ans = freqQuery(queries);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {
                bufferedWriter.write(
                        ans.stream()
                                .map(Object::toString)
                                .collect(joining("\n"))
                                + "\n");
            }
        }
    }


    //328
    //1162
    //66561
    static String fizzbuzzValue(final int n) {
        final boolean divisibleByThree = n % 3 == 0;
        final boolean divisibleByFive = n % 5 == 0;

        if (divisibleByThree && divisibleByFive) {
            return "FizzBuzz";
        } else if (divisibleByThree) {
            return "Fizz";
        } else if (divisibleByFive) {
            return "Buzz";
        }
        return Integer.toString(n);
    }

    static boolean nestedParens(final String s) {
        int openParens = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openParens++;
            } else {
                openParens--;
            }
            if (openParens < 0) {
                return false;
            }
        }
        return openParens == 0;
    }

    private static int mergeAndCount(int[] arr, int l, int m, int r) {

        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }

        // Fill from the rest of the left subarray
        while (i < left.length)
            arr[k++] = left[i++];

        // Fill from the rest of the right subarray
        while (j < right.length)
            arr[k++] = right[j++];

        return swaps;
    }

    // Merge sort function
    private static int mergeSortAndCount(int[] arr, int l, int r) {

        // Keeps track of the inversion count at a
        // particular node of the recursion tree
        int count = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Total inversion count = left subarray count" "
            // + right subarray count + merge count" "

            // Left subarray count" "
            count += mergeSortAndCount(arr, l, m);

            // Right subarray count" "
            count += mergeSortAndCount(arr, m + 1, r);

            // Merge count
            count += mergeAndCount(arr, l, m, r);
        }

        return count;
    }

    static List<String> allParens(final int n) {
        if (n <= 0) return List.of("");
        if (n == 1) return List.of("()");

        return allParens(n - 1).stream()
                .flatMap(parens -> List.of("(" + parens + ")", "()" + parens, parens + "()").stream())
                .distinct()
                .collect(Collectors.toList());

    }

    static int maxNotPresent(int[] a, int[] b) {
        final Map<Integer, List<Integer>> facingUpNumbers = new HashMap<>();
        final Map<Integer, List<Integer>> facingDownNumbers = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            int facingUpNumberAtCurrentIndex = a[i];
            if (facingUpNumbers.containsKey(facingUpNumberAtCurrentIndex)) {
                facingUpNumbers.get(facingUpNumberAtCurrentIndex).add(i);
            } else {
                final List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                facingUpNumbers.put(facingUpNumberAtCurrentIndex, indexList);
            }
            int facingDownNumberAtCurrentIndex = b[i];
            if (facingDownNumbers.containsKey(facingDownNumberAtCurrentIndex)) {
                facingDownNumbers.get(facingDownNumberAtCurrentIndex).add(i);
            } else {
                final List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                facingDownNumbers.put(facingDownNumberAtCurrentIndex, indexList);
            }
        }

        for (int i = 1; i < 100000000; i++) {
            if (!facingUpNumbers.containsKey(i) && !facingDownNumbers.containsKey(i)) {
                System.out.println("no such number");
                for (int index : a) {
                    System.out.print(index);
                }
                System.out.println();
                for (int index : facingUpNumbers.keySet()) {
                    System.out.print(index);
                }
                System.out.println();
                for (int index : b) {
                    System.out.print(index);
                }
                System.out.println();
                for (int index : facingDownNumbers.keySet()) {
                    System.out.print(index);
                }
                System.out.println();

                return i;
            }
            if (facingUpNumbers.containsKey(i)) continue;

            final List<Integer> indicesWithCorrectNumber = facingDownNumbers.get(i);

            if (allFrontfacingValuesAreSmallerAndOccurOnlyOnce(i, indicesWithCorrectNumber, a, facingUpNumbers)) {
                System.out.println("all Frontfacing Values Are Smaller And Occur Only Once");
                for (int index : a) {
                    System.out.print(index);
                }
                System.out.println();
                for (int index : facingUpNumbers.keySet()) {
                    System.out.print(index);
                }
                System.out.println();
                for (int index : b) {
                    System.out.print(index);
                }
                System.out.println();
                for (int index : facingDownNumbers.keySet()) {
                    System.out.print(index);
                }
                System.out.println();
                return i;
            }

            final int bestSmallerIndexToFlip = getBestSmallerFlippableFrontfacingValue(i, indicesWithCorrectNumber, a, facingUpNumbers);
            final int bestLargerIndexToFlip = getBestBiggerFlippableFrontfacingValue(i, indicesWithCorrectNumber, a, facingUpNumbers);

            final int indexToFlip = bestSmallerIndexToFlip != -1 ? bestSmallerIndexToFlip : bestLargerIndexToFlip;

            int frontfacingValue = a[indexToFlip];
            int backfacingValue = b[indexToFlip];

            a[indexToFlip] = backfacingValue;
            b[indexToFlip] = frontfacingValue;

            final List<Integer> frontfacingIndices = facingUpNumbers.get(frontfacingValue);
            if (frontfacingIndices.size() == 1) {
                facingUpNumbers.remove(frontfacingValue);
            } else {
                frontfacingIndices.removeIf(value -> value == indexToFlip);
            }
            final List<Integer> newFrontfacingIndices = new ArrayList<>();
            newFrontfacingIndices.add(indexToFlip);
            facingUpNumbers.put(backfacingValue, newFrontfacingIndices);

            final List<Integer> backfacingIndices = facingDownNumbers.get(backfacingValue);
            if (backfacingIndices.size() == 1) {
                facingDownNumbers.remove(backfacingValue);
            } else {
                backfacingIndices.removeIf(value -> value == indexToFlip);
            }
            final List<Integer> newBackfacingIndices = new ArrayList<>();
            newBackfacingIndices.add(indexToFlip);
            facingDownNumbers.put(frontfacingValue, newBackfacingIndices);

        }
        return 1000001;
    }

    static boolean allFrontfacingValuesAreSmallerAndOccurOnlyOnce(
            final int number,
            final List<Integer> indices,
            final int[] frontFacingNumbers,
            final Map<Integer, List<Integer>> frontFacingNumbersToIndices) {
        for (int index : indices) {
            //System.out.println(number + " "+ index);
            if (frontFacingNumbers[index] > number) return false;
            if (frontFacingNumbersToIndices.get(frontFacingNumbers[index]).size() > 1) return false;
        }
        double x = 947853 * 4453;
        return true;
    }

    static int getBestSmallerFlippableFrontfacingValue(
            final int number,
            final List<Integer> indices,
            final int[] frontFacingNumbers,
            final Map<Integer, List<Integer>> frontFacingNumbersToIndices) {
        int indexWithMaxValue = -1;
        int maxValue = 0;
        int indexWithMaxEntries = -1;
        int numberOfEntries = 0;
        for (int index : indices) {
            if (frontFacingNumbers[index] < number) continue;

            if (frontFacingNumbers[index] > maxValue) {
                indexWithMaxValue = index;
                maxValue = frontFacingNumbers[index];
            }

            if (frontFacingNumbersToIndices.get(frontFacingNumbers[index]).size() > numberOfEntries) {
                indexWithMaxEntries = index;
                numberOfEntries = frontFacingNumbersToIndices.get(frontFacingNumbers[index]).size();
            }
        }
        return indexWithMaxEntries != -1 ? indexWithMaxEntries : indexWithMaxValue;
    }

    static int getBestBiggerFlippableFrontfacingValue(
            final int number,
            final List<Integer> indices,
            final int[] frontFacingNumbers,
            final Map<Integer, List<Integer>> frontFacingNumbersToIndices) {
        int indexWithMaxEntries = -1;
        int numberOfEntries = 0;
        for (int index : indices) {
            if (frontFacingNumbers[index] > number) continue;
            if (frontFacingNumbersToIndices.get(frontFacingNumbers[index]).size() == 1) continue;

            if (frontFacingNumbersToIndices.get(frontFacingNumbers[index]).size() > numberOfEntries) {
                indexWithMaxEntries = index;
                numberOfEntries = frontFacingNumbersToIndices.get(frontFacingNumbers[index]).size();
            }
        }
        return indexWithMaxEntries;
    }

    interface Animal {
    }

    static class SetOfStacks<T> {
        final int TRESHOLD = 3;

        final List<Stack<T>> stacks;
        int currentStackIndex;
        int currentStackHeight;

        public SetOfStacks() {
            stacks = new ArrayList<>();
            stacks.add(new Stack<>());

            currentStackIndex = 0;
            currentStackHeight = 0;
        }

        public boolean empty() {
            return currentStackIndex == 0 && currentStackHeight == 0;
        }

        public T pop() {
            if (empty()) return null;

            Stack<T> currentStack = stacks.get(currentStackIndex);
            if (currentStack.empty()) {
                currentStackIndex--;
                currentStack = stacks.get(currentStackIndex);
                currentStackHeight = TRESHOLD;
            }

            currentStackHeight--;
            return currentStack.pop();
        }

        public void push(T value) {
            if (currentStackHeight == TRESHOLD) {
                currentStackIndex++;
                currentStackHeight = 0;
                if (stacks.size() == currentStackIndex) {
                    stacks.add(new Stack<>());
                }
            }
            Stack<T> currentStack = stacks.get(currentStackIndex);
            currentStack.push(value);
            currentStackHeight++;
        }

        public T popAt(int index) {
            if (stacks.size() > index && !stacks.get(index).empty()) {
                return stacks.get(index).pop();
            }
            return null;
        }

        public void printMetrics() {
            System.out.println(currentStackHeight + "@" + currentStackIndex);
        }
    }

    class Dog implements Animal {
        long arrivalTimeStamp;
        String name;
    }

    class Cat implements Animal {
        long arrivalTimeStamp;
        String name;
    }

    class AnimalShelter {

        LinkedList<Cat> cats;
        LinkedList<Dog> dogs;

        public AnimalShelter() {
            cats = new LinkedList<>();
            dogs = new LinkedList<>();
        }

        public void enqueue(final Animal animal) {
            if (animal instanceof Cat) {
                cats.add((Cat) animal);
            } else {
                dogs.add((Dog) animal);
            }
        }

        public Animal dequeueAny() {
            if (cats.isEmpty() && !dogs.isEmpty()) return dogs.pop();
            if (dogs.isEmpty() && !cats.isEmpty()) return cats.pop();

            if (cats.peek().arrivalTimeStamp < dogs.peek().arrivalTimeStamp) {
                return cats.pop();
            }
            return dogs.pop();
        }

        public Dog dequeueDog() {
            return dogs.pop();
        }

        public Cat dequeueCat() {
            return cats.pop();
        }
    }


}
