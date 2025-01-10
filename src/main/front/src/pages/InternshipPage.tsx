import { InternshipInfoResponse } from "@/api/apiTypes";
import { cancelEnroll, enroll, fetchInternship } from "@/api/internAPI";
import Header from "@/components/Header";
import { Button } from "@/components/ui/button";
import { b, n, s } from "@/types";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

export function InternshipPage() {
  const { id } = useParams<{ id: s }>();
  if (!id) throw new Error("No internship id");

  const [internship, setInternship] = useState<InternshipInfoResponse | null>(
    null
  );
  const [error, setError] = useState<s | null>(null);
  const [loading, setLoading] = useState<b>(true);
  const [isEnrolled, setIsEnrolled] = useState<b>(() => {
    const enrolledIds = JSON.parse(
      localStorage.getItem("enrolledInternships") || "[]"
    );
    return enrolledIds.includes(+id);
  });

  const updateEnrollmentStatus = (internshipId: n, enrolled: b) => {
    const enrolledIds = JSON.parse(
      localStorage.getItem("enrolledInternships") || "[]"
    );
    const updatedIds = enrolled
      ? [...enrolledIds, internshipId]
      : enrolledIds.filter((id: n) => id !== internshipId);

    if (updatedIds.length === 0) {
      localStorage.removeItem("enrolledInternships");
    } else {
      localStorage.setItem("enrolledInternships", JSON.stringify(updatedIds));
    }
    setIsEnrolled(enrolled);
  };

  useEffect(() => {
    loadInternship(+id);
  }, [id]);

  const loadInternship = async (internshipId: n) => {
    setLoading(true);
    setError(null);
    try {
      const data = await fetchInternship(internshipId);
      console.log(data);
      setInternship(data);
    } catch (e) {
      setError(
        e instanceof Error ? e.message : "Failed to fetch internship details."
      );
    } finally {
      setLoading(false);
    }
  };

  const handleEnroll = async () => {
    try {
      await enroll(+id);
      updateEnrollmentStatus(+id, true);
    } catch (e) {
      console.error("Failed to enroll:", e);
    }
  };

  const handleCancelEnroll = async () => {
    try {
      await cancelEnroll(+id);
      updateEnrollmentStatus(+id, false);
    } catch (e) {
      console.error("Failed to cancel enrollment:", e);
    }
  };

  return (
    <div className="px-32 my-8">
      <Header />
      {loading && <div>Loading...</div>}
      {error && <div className="text-red-500">Error: {error}</div>}
      <div className="flex justify-between items-center mb-6">
        <h1 className="font-black text-3xl">{internship?.title}</h1>
      </div>
      <div className="flex justify-between items-center mb-6">
        <p>
          От компании{" "}
          <span className="bg-[#F3DFFF] rounded-full p-2 text-sm">
            {internship?.company}
          </span>
        </p>
        <Link to={`/profile/${internship?.creator.email}`}>
          <div className="flex items-center gap-2 bg-[#F3DFFF] pl-2 rounded-xl">
            <p className="text-sm">{internship?.creator.fio}</p>
            <HrAvatar avatarPath={internship?.creator.avatarPath} />
          </div>
        </Link>
      </div>
      <h2 className="font-bold text-2xl mb-2">Обязанности</h2>
      <p className="mb-6">{internship?.duties}</p>
      <h2 className="font-bold text-2xl mb-2">Требования</h2>
      <p className="mb-6">{internship?.requirements}</p>
      <h2 className="font-bold text-2xl mb-2">Задание</h2>
      <p className="mb-6">{internship?.task}</p>
      <h2 className="font-bold text-2xl mb-2">Теги</h2>
      <div className="flex gap-3 mb-6">
        {internship?.tags.map((t) => (
          <Tag key={t} label={t} />
        ))}
      </div>
      <div className="flex justify-center items-center">
        {isEnrolled ? (
          <div className="flex items-center gap-4 p-4 bg-green-200 rounded-xl">
            <p className="text-green-900">Заявка подана!</p>
            <Button
              className="bg-white text-black font-normal py-1 rounded-full hover:bg-white hover:text-red-500"
              size="sm"
              onClick={handleCancelEnroll}
            >
              Отменить
            </Button>
          </div>
        ) : (
          <Button className="bg-[#8300E7]" onClick={handleEnroll}>
            Подать заявку
          </Button>
        )}
      </div>
    </div>
  );
}

interface HrAvatar {
  avatarPath?: s;
}

export function HrAvatar(p: HrAvatar) {
  // if (!p.avatarPath) console.error("No avatar");
  return (
    <div
      className="rounded-xl w-12 h-12 bg-[#E4C1FF] cursor-pointer"
      style={{
        backgroundImage: `url(${p.avatarPath})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
      tabIndex={0}
      role="button"
    />
  );
}

interface TagProps {
  label: s;
}

function Tag({ label }: TagProps) {
  const purpleShades = ["#8300E7", "#6606b0", "#a63df7", "#8a58b0", "#460578"];

  const hashCode = (str: s) => {
    let hash = 0;
    for (let i = 0; i < str.length; i++)
      hash = str.charCodeAt(i) + ((hash << 5) - hash);

    return Math.abs(hash);
  };

  const colorIndex = hashCode(label) % purpleShades.length;
  const color = purpleShades[colorIndex];

  return (
    <div
      className={`px-3 py-2 rounded-full text-sm text-black`}
      style={{ border: `2px solid ${color}`, color }}
    >
      {label}
    </div>
  );
}
