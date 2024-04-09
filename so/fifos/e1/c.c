#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <fcntl.h>

int main() {
    int status;
    int fd[2] = {-1, -1}; // writer,reader

    // Create the FIFO

    pid_t son = fork();
    if (son == 0) {
        // Execute program 'a'
        execl("./a", "a", NULL);
        perror("Execution of 'a' failed");
        _exit(-1);
    }

    son = wait(&status);
    if (WIFEXITED(status))
        printf("FIFO created successfully\n");

    sleep(1); // Wait for the FIFO to be fully created

    // Writing to and reading from the FIFO
    pid_t pid[2];

    pid[0] = fork();
    if (pid[0] < 0) {
        perror("Writer process fork failed");
        _exit(-1);
    }

    if (pid[0] == 0) {
        // Writer process
        fd[0] = open("./fifo", O_WRONLY);
        if (fd[0] == -1) {
            perror("Error opening FIFO for writing");
            _exit(-1);
        }

        dprintf(fd[0], "Hello from writer\n");
        close(fd[0]);
        _exit(0);
    }

    pid[1] = fork();
    if (pid[1] < 0) {
        perror("Reader process fork failed");
        _exit(-1);
    }

    if (pid[1] == 0) {
        // Reader process
        fd[1] = open("./fifo", O_RDONLY);
        if (fd[1] == -1) {
            perror("Error opening FIFO for reading");
            _exit(-1);
        }

        char buffer[512];
        ssize_t bytes_read;
        while ((bytes_read = read(fd[1], buffer, sizeof(buffer))) > 0) {
            write(STDOUT_FILENO, buffer, bytes_read); // Write to standard output
        }

        close(fd[1]);
        _exit(0);
    }

    // Close the FIFO
    wait(NULL);
    wait(NULL);
    close(fd[0]);
    close(fd[1]);

    // Remove the FIFO
    unlink("./fifo");

    return 0;
}

