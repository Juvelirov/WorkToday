import { signout } from "@/auth";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import IB from "./IB";

export default function Header() {
  return (
    <div className="flex justify-between items-center mb-6">
      <h1 className="text-xl font-bold">
        Work<span className="text-[#8300E7]">Today</span>
      </h1>
      <div className="flex gap-5">
        <div className="flex items-center">
          <IB nameOutlined="notifications_none" nameFilled="notifications" />
        </div>
        <Avatar />
      </div>
    </div>
  );
}

function Avatar() {
  const navigate = useNavigate();
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const toggleMenu = () => setIsMenuOpen(!isMenuOpen);
  const closeMenu = () => setIsMenuOpen(false);

  const menuOptions = [
    { label: "Profile", action: () => navigate("/") },
    { label: "Sign out", action: signout },
  ];

  return (
    <div className="relative">
      <div
        className="rounded-xl w-12 h-12 bg-[#F3DFFF] cursor-pointer"
        onClick={toggleMenu}
        onKeyUp={(e) => {
          if (e.key === "Enter" || e.key === " ") toggleMenu();
        }}
        tabIndex={0}
        role="button"
        aria-pressed={isMenuOpen}
      />

      {isMenuOpen && (
        <div
          className="absolute right-0 mt-2 w-40 bg-white border border-gray-200 rounded-lg shadow-lg"
          onMouseLeave={closeMenu}
        >
          <ul className="py-2">
            {menuOptions.map((option) => (
              <li
                key={option.label}
                onClick={() => {
                  option.action();
                  closeMenu();
                }}
                onKeyDown={(e) => {
                  if (e.key === "Enter" || e.key === " ") {
                    option.action();
                    closeMenu();
                  }
                }}
                className="px-4 py-2 hover:bg-gray-100 cursor-pointer"
              >
                {option.label}
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}
