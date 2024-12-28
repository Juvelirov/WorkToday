import { cn } from "@/lib/utils";
import { s } from "@/types";
import { FileIcon, Download, Trash } from "lucide-react";
import { useEffect, useState } from "react";
import I from "./I";
import IB from "./IB";

interface FileProps {
  title: s;
}

export default function WFile(p: FileProps) {
  const [file, setFile] = useState<File | null>(null);

  // Load the file from localStorage on mount
  useEffect(() => {
    const storedFile = localStorage.getItem(`file-${p.title}`);
    if (storedFile) {
      const parsedFile = JSON.parse(storedFile);
      const fileBlob = new Blob([
        Uint8Array.from(atob(parsedFile.content), (c) => c.charCodeAt(0)),
      ]);
      const file = new File([fileBlob], parsedFile.name, {
        type: parsedFile.type,
      });
      setFile(file);
    }
  }, [p.title]);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = e.target.files?.[0];
    if (selectedFile) {
      const reader = new FileReader();
      reader.onload = () => {
        const base64Content = (reader.result as s).split(",")[1];
        localStorage.setItem(
          `file-${p.title}`,
          JSON.stringify({
            name: selectedFile.name,
            type: selectedFile.type,
            content: base64Content,
          })
        );
        setFile(selectedFile);
      };
      reader.readAsDataURL(selectedFile);
    }
  };

  const handleRemoveFile = () => {
    setFile(null);
    localStorage.removeItem(`file-${p.title}`);
  };

  return (
    <div className={cn("flex flex-col mb-3", file && "mb-5")}>
      <div className={cn("flex justify-between items-center", file && "mb-3")}>
        <h3 className="font-bold text-md">{p.title}</h3>
        {!file && (
          <label
            htmlFor={`file-upload-${p.title}`}
            className="flex justify-center items-center w-9 cursor-pointer bg-[#F3DFFF] rounded-lg "
          >
            <span className="text-2xl pb-1">+</span>
          </label>
        )}
      </div>
      {file ? (
        <FileBlock
          name={file.name}
          setFile={handleRemoveFile}
          fileContent={file}
        />
      ) : (
        <input
          type="file"
          id={`file-upload-${p.title}`}
          className="hidden"
          onChange={handleFileChange}
          accept=".docx,.pdf,.jpg,.png,.txt"
        />
      )}
    </div>
  );
}

interface FileBlock {
  name: s;
  setFile: React.Dispatch<React.SetStateAction<File | null>>;
  fileContent?: File;
}

export function FileBlock(p: FileBlock) {
  const handleDownload = () => {
    if (p.fileContent) {
      const url = URL.createObjectURL(p.fileContent);
      const a = document.createElement("a");
      a.href = url;
      a.download = p.fileContent.name;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
      URL.revokeObjectURL(url);
    }
  };

  return (
    <div className="flex justify-between items-center bg-[#F3DFFF] rounded-lg p-4">
      <div className="flex justify-between items-center gap-1">
        <I icon={FileIcon} />
        <p className="font-semibold text-sm">{p.name}</p>
      </div>
      <div className="flex items-center">
        <IB
          iC="p-1 rounded-full"
          bC="hover:bg-[#FFFFFF]"
          icon={Download}
          onClick={handleDownload}
        />
        <IB
          iC="p-1 rounded-full text-red-500"
          bC="hover:bg-[#FFFFFF]"
          icon={Trash}
          onClick={() => p.setFile(null)}
        />
      </div>
    </div>
  );
}
