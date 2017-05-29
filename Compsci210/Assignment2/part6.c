#include <string.h>
#include <stdio.h>
#include <stdlib.h>

/*Scope*/
void reg_status(int R0, int R1, int R2, int R3, int R4, int R5, int R6, int R7, int PC, int IR);
void condition(int CC);
int operationLD(int instruct, int *op_array, int ogPC, int PC, int CC, int *R0, int *R1, int *R2, int *R3, int *R4, int *R5);
int operationLEA(int instruct, int *op_array, int PC, int CC, int *R0, int *R1, int *R2, int *R3, int *R4, int *R5);
int operationLDI(int instruct, int *op_array, int ogPC, int PC, int CC, int *R0, int *R1, int *R2, int *R3, int *R4, int *R5);
int operationAND(int instruct, int *op_array, int PC, int CC, int *R0, int *R1, int *R2, int *R3, int *R4, int *R5);
int operationNOT(int instruct, int *op_array, int PC, int CC, int *R0, int *R1, int *R2, int *R3, int *R4, int *R5);



int main(int argc, char *argv[])
{
	int R0 = 0, R1 = 0, R2 = 0, R3 = 0, R4 = 0, R5 = 0, R6 = 0, R7 = 0, PC = 0, IR = 0;
	int CC[] = {-1, 0, 1};
	int ogPC;
	unsigned char word[2];
	int op_code;
	int con_code;
	int instruct;
	int op_array[100];
	int op_array_cnt = 0;

	FILE *binary = fopen(argv[1], "r");
	fread(&word, 2, 1, binary);
	PC += ((word[0] << 8) + word[1]);
	ogPC = ((word[0] << 8) + word[1]);
	while(fread(&word, 2, 1, binary) == 1)
	{
		instruct = ((word[0] << 8) + word[1]);
		op_array[op_array_cnt] = instruct;
		op_array_cnt++;

	}
	fclose(binary);
	op_array_cnt = 0;
	while(op_array[op_array_cnt] != 61477)
	{
		PC += 1;
		IR = op_array[op_array_cnt];
		op_code = (op_array[op_array_cnt] >> 12);
    /*LD*/
		if(op_code == 2)
		{
			con_code = operationLD(op_array[op_array_cnt], op_array, ogPC, PC, *CC, &R0, &R1, &R2, &R3, &R4, &R5);
		}
    /*AND*/
    else if(op_code == 5)
    {
      con_code = operationAND(op_array[op_array_cnt], op_array, PC, *CC, &R0, &R1, &R2, &R3, &R4, &R5);
    }
    /*NOT*/
    else if(op_code == 9)
    {
      con_code = operationNOT(op_array[op_array_cnt], op_array, PC, *CC, &R0, &R1, &R2, &R3, &R4, &R5);
			printf("after executing instruction\t0x%x\n", IR);
			reg_status(R0, R1, R2, R3, R4, R5, R6, R7, PC, IR);
			condition(CC[con_code]);
    }
    /*LDI*/
    else if(op_code == 10)
    {
      con_code = operationLDI(op_array[op_array_cnt], op_array, ogPC, PC, *CC, &R0, &R1, &R2, &R3, &R4, &R5);
    }
    /*LEA*/
		else if(op_code == 14)
		{
			con_code = operationLEA(op_array[op_array_cnt], op_array, PC, *CC, &R0, &R1, &R2, &R3, &R4, &R5);
		}
		op_array_cnt++;
	}

	return 0;
}
int operationLD(int instruct, int *op_array, int ogPC, int PC, int CC, int *R0, int *R1, int *R2, int *R3, int *R4, int *R5)
{
	int reg_used;
	int offset;
	int CC_ptr;
  int PC_leading_bit;
	reg_used = (instruct & 3584) >> 9;
	offset = instruct & 511;
	PC += offset;
	PC -= ogPC;
  PC_leading_bit = PC >> 15;

	if(PC_leading_bit == 1)
	{
		CC_ptr = 0;
	}
	else if(PC == 0)
	{
		CC_ptr = 1;
	}
	else
	{
		CC_ptr = 2;
	}

	if(reg_used == 0)
	{
		*R0 = op_array[PC];
	}
	else if(reg_used == 1)
	{
		*R1 = op_array[PC];
	}
	else if(reg_used == 2)
	{
		*R2 = op_array[PC];
	}
	else if(reg_used == 3)
	{
		*R3 = op_array[PC];
	}
	else if(reg_used == 4)
	{
		*R4 = op_array[PC];
	}
	else
	{
		*R5 = op_array[PC];
	}
	return CC_ptr;

}

int operationLEA(int instruct, int *op_array, int PC, int CC, int *R0, int *R1, int *R2, int *R3, int *R4, int *R5)
{
	int reg_used;
	int offset;
	int CC_ptr;
  int PC_leading_bit;
	reg_used = (instruct & 3584) >> 9;
	offset = instruct & 511;
	PC += offset;
  PC_leading_bit = PC >> 15;

	if(PC_leading_bit == 1)
	{
		CC_ptr = 0;
	}
	else if(PC == 0)
	{
		CC_ptr = 1;
	}
	else
	{
		CC_ptr = 2;
	}

	if(reg_used == 0)
	{
		*R0 = PC;
	}
	else if(reg_used == 1)
	{
		*R1 = PC;
	}
	else if(reg_used == 2)
	{
		*R2 = PC;
	}
	else if(reg_used == 3)
	{
		*R3 = PC;
	}
	else if(reg_used == 4)
	{
		*R4 = PC;
	}
	else
	{
		*R5 = PC;
	}
	return CC_ptr;
}

int operationLDI(int instruct, int *op_array, int ogPC, int PC, int CC, int *R0, int *R1, int *R2, int *R3, int *R4, int *R5)
{
	int reg_used;
	int offset;
	int CC_ptr;
  int PC_leading_bit;
	reg_used = (instruct & 3584) >> 9;
	offset = instruct & 511;
	PC += offset;
  PC -= ogPC;
  PC = op_array[PC];
  PC -= ogPC;
  PC = op_array[PC];
  PC_leading_bit = PC >> 15;


	if(PC_leading_bit == 1)
	{
		CC_ptr = 0;
	}
	else if(PC == 0)
	{
		CC_ptr = 1;
	}
	else
	{
		CC_ptr = 2;
	}

	if(reg_used == 0)
	{
		*R0 = PC;
	}
	else if(reg_used == 1)
	{
		*R1 = PC;
	}
	else if(reg_used == 2)
	{
		*R2 = PC;
	}
	else if(reg_used == 3)
	{
		*R3 = PC;
	}
	else if(reg_used == 4)
	{
		*R4 = PC;
	}
	else
	{
		*R5 = PC;
	}
	return CC_ptr;
}

int operationAND(int instruct, int *op_array, int PC, int CC, int *R0, int *R1, int *R2, int *R3, int *R4, int *R5)
{
  int value_a;
  int value_b;
  int answer;
  int DR;
  int SR1;
  int con_code;
  int leading_bit;
  DR = (instruct & 3584) >> 9;
  SR1 = (instruct & 448) >> 6;

  if(SR1 == 0)
  {
    value_a = *R0;
  }
  else if(SR1 == 1)
  {
    value_a = *R1;
  }
  else if(SR1 == 2)
  {
    value_a = *R2;
  }
  else if(SR1 == 3)
  {
    value_a = *R3;
  }
  else if(SR1 == 4)
  {
    value_a = *R4;
  }
  else
  {
    value_a = *R5;
  }

  if((instruct & 32) == 0)
  {
    int SR2;
    SR2 = instruct & 7;
    if(SR2 == 0)
    {
      value_b = *R0;
    }
    else if(SR2 == 1)
    {
      value_b = *R1;
    }
    else if(SR2 == 2)
    {
      value_b = *R2;
    }
    else if(SR2 == 3)
    {
      value_b = *R3;
    }
    else if(SR2 == 4)
    {
      value_b = *R4;
    }
    else
    {
      value_b = *R5;
    }
  }
  else
  {
    value_b = instruct & 31;
  }
  answer = value_a & value_b;
  leading_bit = answer >> 15;
  if(leading_bit == 1)
  {
    con_code = 0;
  }
  else if(answer == 0)
  {
    con_code = 1;
  }
  else
  {
    con_code = 2;
  }


  if(DR == 0)
  {
    *R0 = answer;
  }
  else if(DR == 1)
  {
    *R1 = answer;
  }
  else if(DR == 2)
  {
    *R2 = answer;
  }
  else if(DR == 3)
  {
    *R3 = answer;
  }
  else if(DR == 4)
  {
    *R4 = answer;
  }
  else
  {
    *R5 = answer;
  }
  return con_code;
}
int operationNOT(int instruct, int *op_array, int PC, int CC, int *R0, int *R1, int *R2, int *R3, int *R4, int *R5)
{
  int value_a;
  int answer;
  int DR;
  int SR1;
  int con_code;
  int leading_bit;
  DR = (instruct & 3584) >> 9;
  SR1 = (instruct & 448) >> 6;

  if(SR1 == 0)
  {
    value_a = *R0;
  }
  else if(SR1 == 1)
  {
    value_a = *R1;
  }
  else if(SR1 == 2)
  {
    value_a = *R2;
  }
  else if(SR1 == 3)
  {
    value_a = *R3;
  }
  else if(SR1 == 4)
  {
    value_a = *R4;
  }
  else
  {
    value_a = *R5;
  }


  answer = ~ value_a;
  leading_bit = answer >> 15;
  if(leading_bit == 0)
  {
    con_code = 2;
  }
  else if(answer == 0)
  {
    con_code = 1;
  }
  else
  {
    con_code = 0;
  }


  if(DR == 0)
  {
    *R0 = answer;
  }
  else if(DR == 1)
  {
    *R1 = answer;
  }
  else if(DR == 2)
  {
    *R2 = answer;
  }
  else if(DR == 3)
  {
    *R3 = answer;
  }
  else if(DR == 4)
  {
    *R4 = answer;
  }
  else
  {
    *R5 = answer;
  }
  return con_code;
}

void reg_status(int R0, int R1, int R2, int R3, int R4, int R5, int R6, int R7, int PC, int IR)
{
	printf("R0\t0x%04hx\n", R0);
	printf("R1\t0x%04hx\n", R1);
	printf("R2\t0x%04hx\n", R2);
	printf("R3\t0x%04hx\n", R3);
	printf("R4\t0x%04hx\n", R4);
	printf("R5\t0x%04hx\n", R5);
	printf("R6\t0x%04hx\n", R6);
	printf("R7\t0x%04hx\n", R7);
	printf("PC\t0x%04hx\n", PC);
	printf("IR\t0x%04hx\n", IR);
}
void condition(int CC) {
		char con_code;
		if (CC == -1)
		{
			con_code = 'N';
		}
		else if (CC == 0)
		{
			con_code = 'Z';
		}
		else
		{
			con_code = 'P';
		}
		printf("CC\t%c\n", con_code);
		printf("==================\n");
}
