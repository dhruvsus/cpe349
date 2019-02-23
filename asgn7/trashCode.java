while (table[i][j] != 0) {
                System.out.println(table[i][j]);
                String temp = "";
                System.out.printf("value at table[i][j] = %d, x %s y %s\n", table[i][j], x[j-1], y[i-1]);
                // check if it was a match
                if (table[i][j] == table[i - 1][j - 1]) {
                    temp = x[j] + " " + y[i] + " " + "0";
                    backTrace[backTraceIndex] = temp;
                    i--;
                    j--;
                    backTraceIndex--;
                } else {
                    // check if empty, ie table[i-1][j] or table[i][j-1] +2
                    if (table[i][j] == table[i - 1][j] + 2) {
                        temp = x[j] + " " + y[i - 1] + " " + "2";
                        backTrace[backTraceIndex] = temp;
                        i--;
                        backTraceIndex--;
                    } else if (table[i][j] == table[i][j - 2] + 2) {
                        temp = x[j - 1] + " " + y[i] + " " + "2";
                        backTrace[backTraceIndex] = temp;
                        j--;
                        backTraceIndex--;
                    } else if (table[i][j] == table[i - 1][j - 1] + 1) {
                        temp = x[j] + " " + y[i] + " " + "1";
                        backTrace[backTraceIndex] = temp;
                        i--;
                        j--;
                        backTraceIndex--;
                    }
                }
            }