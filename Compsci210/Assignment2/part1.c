#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
	unsigned char word[2];
	FILE *binary = fopen(argv[1], "r");
	while (fread(&word, 2, 1, binary) == 1)
		{
		printf("0x%04x\n", (word[0] << 8) + word[1]);
		}
	fclose(binary);
	return 0;
}
