import tkinter as tk
from tkinter import messagebox

# Create the main application window
root = tk.Tk()
root.title("Tic Tac Toe")

# Game variables
current_player = "X"
board = ["" for _ in range(9)]

def check_winner():
    """Check if there is a winner or a draw."""
    winning_combinations = [
        (0, 1, 2), (3, 4, 5), (6, 7, 8),  # Horizontal
        (0, 3, 6), (1, 4, 7), (2, 5, 8),  # Vertical
        (0, 4, 8), (2, 4, 6)              # Diagonal
    ]

    for a, b, c in winning_combinations:
        if board[a] == board[b] == board[c] and board[a] != "":
            return board[a]

    if "" not in board:
        return "Draw"

    return None

def on_click(button, index):
    """Handle button click for a cell."""
    global current_player

    if board[index] == "":
        board[index] = current_player
        button.config(text=current_player, state=tk.DISABLED)
        winner = check_winner()

        if winner:
            if winner == "Draw":
                messagebox.showinfo("Game Over", "It's a draw!")
            else:
                messagebox.showinfo("Game Over", f"Player {winner} wins!")
            reset_game()
        else:
            current_player = "O" if current_player == "X" else "X"
    else:
        messagebox.showwarning("Invalid Move", "This cell is already taken!")

def reset_game():
    """Reset the game to its initial state."""
    global current_player, board
    current_player = "X"
    board = ["" for _ in range(9)]

    for button in buttons:
        button.config(text="", state=tk.NORMAL)

# Create the game grid
buttons = []
for i in range(9):
    button = tk.Button(root, text="", font=("Arial", 24), height=2, width=5, command=lambda i=i: on_click(buttons[i], i))
    button.grid(row=i // 3, column=i % 3)
    buttons.append(button)

# Run the application
root.mainloop()
