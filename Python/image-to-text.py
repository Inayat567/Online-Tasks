import tkinter as tk
from tkinter import filedialog, scrolledtext
from PIL import Image
import pytesseract
import os

# Set Tesseract data directory
#os.environ['TESSDATA_PREFIX'] = '/usr/share/tesseract-ocr/4.0/tessdata/'  # Adjust path as per your installation
os.environ["TESSDATA_PREFIX"] = "" # Leaving it empty because file is already copy pasted in the current directory
print(os.getenv("TESSDATA_PREFIX"))
# Function to extract text from an image file
def extract_text_from_image(image_path):
    try:
        # Open the image file
        image = Image.open(image_path)
        
        # Use Tesseract to do OCR on the image, specifying languages
        text = pytesseract.image_to_string(image, lang='eng')
        
        return text
    except Exception as e:
        return f"Error: {str(e)}"

# Function to handle file dialog and extraction
def open_image_file():
    try:
        file_path = filedialog.askopenfilename(filetypes=[("Image files", "*.png")])
        if file_path:
            text = extract_text_from_image(file_path)
            display_text(text)
    except Exception as e:
        print(f"Error opening image file: {str(e)}")

# Function to display extracted text in a new window
def display_text(text):
    text_window = tk.Toplevel(root)
    text_window.title("Extracted Text")

    text_area = scrolledtext.ScrolledText(text_window, wrap=tk.WORD, width=100, height=30)
    text_area.pack(expand=True, fill='both')

    text_area.insert(tk.END, text)

# Main Tkinter window setup
root = tk.Tk()
root.title("Image to Text Converter")

# Button to select an image file
open_button = tk.Button(root, text="Select Image File", command=open_image_file)
open_button.pack(pady=20)

# Start the Tkinter event loop
root.mainloop()
