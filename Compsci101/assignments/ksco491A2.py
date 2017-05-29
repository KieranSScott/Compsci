import random
def check_game_finished(game_string):
	first_four_symbols = game_string[0:4]
	if first_four_symbols == "$$$$":
		return True
	
	return False


def display_menu():
	print("1. PLAY COIN STRIP")
	print("2. EXIT")
	player_selection = int(input("Enter selection: "))
	if player_selection == 1:
		return True
	elif player_selection == 2:
		return False
		
	
def move_dollar_to_the_left(game_string, position_number, to_move):
	position_number = position_number - 1
	symbol = game_string[position_number]
	new_position_number = (position_number) - (to_move)
	game_string = game_string[:new_position_number] + symbol + game_string[new_position_number:position_number] + game_string[position_number + 1:]
	return game_string
	
def get_next_player_num(player_num):
	if player_num == 1:
		return 2
	else:
		return 1

def congratulate_player(player_num):
	print("=========================")
	print("** Y O U H A V E W O N **")
	print("     PLAYER NUMBER: " + str(player_num))
	print("** Y O U H A V E W O N **")
	print("=========================")
	
def get_num_to_move():
	number_to_move = int(input("Enter number to move: "))
	return number_to_move

def get_user_position_num(player_num):
	print("PLAYER NUMBER: ", player_num)
	player_position_num = int(input("Enter position number: "))
	return player_position_num
	
def display_game_string(game_line):
	digits_line = "    1     2     3     4     5     6     7     8     9    "
	seperators = "||     |     |     |     |     |     |     |     |     ||"
	game_string = "||  " + game_line[0] + "  |  " + game_line[1] + "  |  " + game_line[2] + "  |  " + game_line[3] + "  |  " + game_line[4] + "  |  " + game_line[5] + "  |  " + game_line[6] + "  |  " + game_line[7] + "  |  " + game_line[8] + "  ||"
	print()
	print(digits_line)
	print(seperators)
	print(game_string)
	print(seperators)
	print()

def jumble_game_line(game_line):
	random_position = random.randrange(0,9)
	part1_of_game_line = game_line[:random_position]
	part2_of_game_line = game_line[random_position:random_position + 1]
	part3_of_game_line = game_line[random_position + 1:9]
	return part1_of_game_line + part3_of_game_line + part2_of_game_line
	 
	
def create_game_string():
	game_string = " $ $ $ $ "
	for i in range(8):
		game_string = jumble_game_line(game_string)
		
	return game_string
	
def play_one_game():
	player_num = 1
	game_finished = False
	game_string = create_game_string()
	while game_finished == False:
		display_game_string(game_string)
		position_num = get_user_position_num(player_num)
		move_num = get_num_to_move()
		game_string = move_dollar_to_the_left(game_string, position_num, move_num)
		game_finished = check_game_finished(game_string)
		if game_finished:
			display_game_string(game_string)
			congratulate_player(player_num)
		else:
			player_num = get_next_player_num(player_num)
			

def main():
	print("Welcome to coin strip")
	user_wants_to_play = display_menu()
	while user_wants_to_play:
		play_one_game()
		players_choice = display_menu()
		if players_choice == True:
			user_wants_to_play = True
		else:
			user_wants_to_play = False
	print()
	print("BYE FROM COIN STRIP")
	pass
main()