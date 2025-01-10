import { cn } from "@/lib/utils";
import { F, n, P, s, v } from "@/types";
import { FileIcon, Download, Trash } from "lucide-react";
import { useState } from "react";
import I from "./I";
import IB from "./IB";
import { FileResponse } from "@/api/apiTypes";

interface WFile {
  title: s;
  onRefresh: F<v>;
  onUpload: (newFile: File, url: s) => P<v>;
  onDelete: (id: n) => P<v>;
  file?: FileResponse;
}

export function WFile(p: WFile) {
  const [filePath, setFilePath] = useState(p.file?.filePath || "");
  console.log("beginning:", p.file);

  const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = e.target.files?.[0];
    if (selectedFile) {
      const reader = new FileReader();
      reader.onload = () => {
        const base64Content = (reader.result as s).split(",")[1];
        setFilePath(base64Content);
      };
      reader.readAsDataURL(selectedFile);
      await p.onUpload(selectedFile, "");
      p.onRefresh();
    }
  };

  const handleRemoveFile = async () => {
    await p.onDelete(p.file?.id as n);
    setFilePath("");
  };

  return (
    <div className={cn("flex flex-col mb-3", filePath && "mb-5")}>
      <div
        className={cn("flex justify-between items-center", filePath && "mb-3")}
      >
        <h3 className="font-bold text-md">{p.title}</h3>
        {!filePath && (
          <label
            htmlFor={`file-upload-${p.title}`}
            className="flex justify-center items-center w-9 cursor-pointer bg-[#F3DFFF] rounded-lg "
          >
            <span className="text-2xl pb-1">+</span>
          </label>
        )}
      </div>
      {filePath ? (
        <FileBlock filePath={filePath} handleDelete={handleRemoveFile} />
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
  filePath: s;
  handleDelete: () => P<v>;
}

export function FileBlock(p: FileBlock) {
  const handleDownload = () => {
    const a = document.createElement("a");
    a.href = p.filePath;
    // a.download = ;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
  };

  return (
    <div className="flex justify-between items-center bg-[#F3DFFF] rounded-lg p-4">
      <div className="flex justify-between items-center gap-1">
        <I icon={FileIcon} />
        {/* <p className="font-semibold text-sm">{p.fileName}</p> */}
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
          onClick={p.handleDelete}
        />
      </div>
    </div>
  );
}

// interface ResumeFile {
//   title: s;
//   file: ResumeResponse | undefined;
//   onRefresh: F<v>;
// }

// export function ResumeFile(p: ResumeFile) {
//   const [filePath, setFilePath] = useState(p.file?.filePath || "");

//   const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
//     const selectedFile = e.target.files?.[0];
//     if (selectedFile) {
//       const reader = new FileReader();
//       reader.onload = () => {
//         const base64Content = (reader.result as s).split(",")[1];

//         setFilePath(base64Content);
//       };
//       reader.readAsDataURL(selectedFile);

//       await createResume(selectedFile, "");
//       p.onRefresh();
//     }
//   };

//   const handleRemoveFile = async () => {
//     try {
//       await deleteResume(p.file?.id as n);
//       setFilePath("");
//       console.log(p.file);
//     } catch (e) {
//       console.log("Couldn't delete resume:", e);
//     }
//   };

//   return (
//     <div className={cn("flex flex-col mb-3", filePath && "mb-5")}>
//       <div
//         className={cn("flex justify-between items-center", filePath && "mb-3")}
//       >
//         <h3 className="font-bold text-md">{p.title}</h3>
//         {!filePath && (
//           <label
//             htmlFor={`file-upload-${p.title}`}
//             className="flex justify-center items-center w-9 cursor-pointer bg-[#F3DFFF] rounded-lg "
//           >
//             <span className="text-2xl pb-1">+</span>
//           </label>
//         )}
//       </div>
//       {filePath ? (
//         <ResumeFileBlock filePath={filePath} deleteFile={handleRemoveFile} />
//       ) : (
//         <input
//           type="file"
//           id={`file-upload-${p.title}`}
//           className="hidden"
//           onChange={handleFileChange}
//           accept=".docx,.pdf,.jpg,.png,.txt"
//         />
//       )}
//     </div>
//   );
// }

// interface ResumeFileBlock {
//   filePath: s;
//   deleteFile: () => P<v>;
// }

// export function ResumeFileBlock(p: ResumeFileBlock) {
//   const handleDownload = () => {
//     const a = document.createElement("a");
//     a.href = p.filePath; // Use the filePath from the server
//     // a.download = p.fileName; // Use the filename from the path
//     document.body.appendChild(a);
//     a.click();
//     document.body.removeChild(a);
//   };

//   return (
//     <div className="flex justify-between items-center bg-[#F3DFFF] rounded-lg p-4">
//       <div className="flex justify-between items-center gap-1">
//         <I icon={FileIcon} />
//         {/* <p className="font-semibold text-sm">{p.fileName}</p> */}
//       </div>
//       <div className="flex items-center">
//         <IB
//           iC="p-1 rounded-full"
//           bC="hover:bg-[#FFFFFF]"
//           icon={Download}
//           onClick={handleDownload}
//         />
//         <IB
//           iC="p-1 rounded-full text-red-500"
//           bC="hover:bg-[#FFFFFF]"
//           icon={Trash}
//           onClick={p.deleteFile}
//         />
//       </div>
//     </div>
//   );
// }
