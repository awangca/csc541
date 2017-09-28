### README 

## Steps:

1. download the source files from os-book.com
2. unzip it
3. go to the located directary in a terminal
4. run command 'make' to compile it
5. run command 'sudo insmod simple.ko' to load kernel modules
6. run command 'lsmod' to check if it's loaded successfully 
7. rum command 'dmesg' to check the message in the kernel log buffer
8. run command 'sudo rmmod simple'
9. run command 'sudo dmesg -c' to clear the buffer