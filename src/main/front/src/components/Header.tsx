import Avatar from "./Avatar";

export default function Header() {
  return (
    <div className="flex justify-between items-center mb-6 bg-white">
      <h1 className="text-xl font-bold">
        Work<span className="text-[#8300E7]">Today</span>
      </h1>
      <Avatar />
    </div>
  );
}
