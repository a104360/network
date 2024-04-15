#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

const char message[] = "Jipow é gay. Não há contestação.\n";

int main() {
    int fd = open("tmp", O_CREAT | O_RDWR | O_TRUNC, 0666);
    if (fd == -1) {
        perror("Error opening file");
        return 1;
    }

    write(fd, message, sizeof(message));

    lseek(fd,0,SEEK_SET);
    char buffer[11]; // Increased buffer size to accommodate null terminator
    ssize_t bytesRead;

    while ((bytesRead = read(fd, buffer, sizeof(char) * 10)) > 0) {
        buffer[bytesRead] = '\0'; // Null-terminate the buffer
        printf("%s", buffer); // Add a newline character here
    }

    close(fd);
    return 0;
}

