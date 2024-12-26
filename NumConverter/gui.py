import subprocess
import tkinter as tk
from tkinter import messagebox

# Function to call the C++ program
def convert_number():
    input_number = entry_number.get()
    input_base = entry_input_base.get()
    output_base = entry_output_base.get()

    if not input_number or not input_base or not output_base:
        messagebox.showerror("Error", "All fields are required!")
        return

    try:
        # Call the compiled C++ program
        result = subprocess.run(
            ["convert.exe"],  # Replace with "converter.exe" on Windows
            input=f"{input_number}\n{input_base}\n{output_base}\n",
            text=True,
            capture_output=True,
            check=True
        )
        output = result.stdout.strip()
        label_result.config(text=f"Result: {output}")
    except subprocess.CalledProcessError as e:
        messagebox.showerror("Error", f"An error occurred:\n{e.stderr}")
    except FileNotFoundError:
        messagebox.showerror("Error", "The C++ program was not found!")

# Create the GUI
root = tk.Tk()
root.title("Number System Converter")

# Input fields
frame = tk.Frame(root, padx=10, pady=10)
frame.pack(padx=10, pady=10)

tk.Label(frame, text="Number to Convert:").grid(row=0, column=0, sticky="w")
entry_number = tk.Entry(frame)
entry_number.grid(row=0, column=1)

tk.Label(frame, text="Input Base (2, 8, 10, 16):").grid(row=1, column=0, sticky="w")
entry_input_base = tk.Entry(frame)
entry_input_base.grid(row=1, column=1)

tk.Label(frame, text="Output Base (2, 8, 10, 16):").grid(row=2, column=0, sticky="w")
entry_output_base = tk.Entry(frame)
entry_output_base.grid(row=2, column=1)

# Convert button
button_convert = tk.Button(frame, text="Convert", command=convert_number)
button_convert.grid(row=3, column=0, columnspan=2, pady=10)

# Result label
label_result = tk.Label(frame, text="Result: ", font=("Arial", 12), fg="blue")
label_result.grid(row=4, column=0, columnspan=2)

# Run the GUI loop
root.mainloop()
