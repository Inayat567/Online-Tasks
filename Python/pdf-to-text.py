import tkinter as tk
from tkinter import filedialog, scrolledtext
import fitz  # PyMuPDF
from PIL import Image
import pytesseract
import io

# Set Tesseract path if not in system path
# pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'

def extract_text_from_pdf(pdf_path):
    pdf_document = fitz.open(pdf_path)
    all_text = ""

    for page_num in range(len(pdf_document)):
        page = pdf_document.load_page(page_num)
        
        images = page.get_images(full=True)
        
        if images:
            for img_index, img in enumerate(images):
                xref = img[0]
                base_image = pdf_document.extract_image(xref)
                image_bytes = base_image["image"]
                image = Image.open(io.BytesIO(image_bytes))
                text = pytesseract.image_to_string(image, lang='ara+eng+urd')
                all_text += text + "\n"
        else:
            text = page.get_text("text")
            all_text += text + "\n"

    return all_text

def open_file():
    file_path = filedialog.askopenfilename(filetypes=[("PDF files", "*.pdf")])
    if file_path:
        text = extract_text_from_pdf(file_path)
        display_text(text)

def display_text(text):
    text_window = tk.Toplevel(root)
    text_window.title("Extracted Text")

    text_area = scrolledtext.ScrolledText(text_window, wrap=tk.WORD, width=100, height=30)
    text_area.pack(expand=True, fill='both')

    text_area.insert(tk.END, text)

root = tk.Tk()
root.title("PDF Text Extractor")

open_button = tk.Button(root, text="Select PDF File", command=open_file)
open_button.pack(pady=20)

root.mainloop()
