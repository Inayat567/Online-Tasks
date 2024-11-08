import tkinter as tk
from tkinter import filedialog, scrolledtext
import fitz  # PyMuPDF

def extract_text_from_pdf(pdf_path):
    # Open the PDF file
    pdf_document = fitz.open(pdf_path)
    all_text = ""

    # Iterate through each page
    for page_num in range(len(pdf_document)):
        page = pdf_document.load_page(page_num)
        all_text += page.get_text()

    return all_text

def open_file():
    file_path = filedialog.askopenfilename(filetypes=[("PDF files", "*.pdf")])
    if file_path:
        text = extract_text_from_pdf(file_path)
        display_text(text)

def display_text(text):
    # Create a new window to display the extracted text
    text_window = tk.Toplevel(root)
    text_window.title("Extracted Text")

    # Create a scrolled text widget
    text_area = scrolledtext.ScrolledText(text_window, wrap=tk.WORD, width=100, height=30)
    text_area.pack(expand=True, fill='both')

    # Insert the extracted text into the text widget
    text_area.insert(tk.END, text)

# Create the main window
root = tk.Tk()
root.title("PDF Text Extractor")

# Create a button to open the file dialog
open_button = tk.Button(root, text="Select PDF File", command=open_file)
open_button.pack(pady=20)

# Start the Tkinter event loop
root.mainloop()