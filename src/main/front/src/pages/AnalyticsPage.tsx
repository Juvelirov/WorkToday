import { AnalyticsResponse, EnrollResponse, ResultDTO } from "@/api/apiTypes";
import {
  fetchEnrolls,
  acceptEnroll,
  rejectEnroll,
  fetchAnalytics,
  setAnalyticsResult,
} from "@/api/hrAPI";
import Header from "@/components/Header";
import { Button } from "@/components/ui/button";
import { b, n, s } from "@/types";
import { useState, useEffect } from "react";

export default function AnalyticsPage() {
  return (
    <div className="px-32 my-8">
      <Header />
      <Interns />
      <Enrolls />
    </div>
  );
}

const statusOptions: AnalyticsResponse["status"][] = [
  "IN_PROGRESS",
  "COMPLETED",
  "REVIEW",
  "NOT_STARTED",
  "REWORK",
];

const recommendationOptions = [
  { value: true, label: "Да" },
  { value: false, label: "Нет" },
];

interface InternState extends AnalyticsResponse {
  editableStatus: AnalyticsResponse["status"];
  editableMark: n;
  editableRecommendation: b;
}

function Interns() {
  const [interns, setInterns] = useState<InternState[]>([]);
  const [loading, setLoading] = useState<b>(true);
  const [error, setError] = useState<s | null>(null);
  const [saving, setSaving] = useState<b>(false);

  useEffect(() => {
    const loadAnalytics = async () => {
      try {
        const data = await fetchAnalytics();
        const initializedData: InternState[] = data.map((intern) => ({
          ...intern,
          editableStatus: intern.status,
          editableMark: intern.mark,
          editableRecommendation: intern.recommendation,
        }));
        setInterns(initializedData);
      } catch (e) {
        setError(`Failed to fetch analytics data: ${e}`);
      } finally {
        setLoading(false);
      }
    };

    loadAnalytics();
  }, []);

  const handleChange = (
    id: n,
    field: "status" | "mark" | "recommendation",
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    value: any
  ) => {
    setInterns((prevInterns) =>
      prevInterns.map((intern) =>
        intern.id === id
          ? {
              ...intern,
              [`editable${capitalize(field)}`]: value,
            }
          : intern
      )
    );
  };

  const handleSave = async (id: n) => {
    const intern = interns.find((intern) => intern.id === id);
    if (!intern) return;

    const result: ResultDTO = {
      status: intern.editableStatus,
      mark: intern.editableMark,
      recommendation: intern.editableRecommendation,
    };

    try {
      setSaving(true);
      await setAnalyticsResult(id, result);
    } catch (e) {
      alert(`Failed to save the result: ${e}`);
    } finally {
      setSaving(false);
    }
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p className="text-red-500">{error}</p>;

  return (
    <div className="flex justify-center mb-10">
      <table className="border-collapse overflow-hidden rounded-xl w-full max-w-4xl">
        <thead className="bg-[#E4C1FF] text-[#8300E7]">
          <tr>
            <th className="p-4 text-start">Стажёр</th>
            <th className="p-4 text-start">Статус стажировки</th>
            <th className="p-4 text-start">Оценка</th>
            <th className="p-4 text-start">Рекомендован</th>
            <th className="p-4 text-start">Действия</th>
          </tr>
        </thead>
        <tbody className="bg-[#F3DFFF]">
          {interns.map((intern) => (
            <tr key={intern.id} className="border-t">
              <td className="p-4">
                <InternProfile fio={intern.fio} />
              </td>
              <td className="p-4">
                <select
                  value={intern.editableStatus}
                  onChange={(e) =>
                    handleChange(
                      intern.id,
                      "status",
                      e.target.value as AnalyticsResponse["status"]
                    )
                  }
                  className="border rounded px-2 py-1"
                >
                  {statusOptions.map((status) => (
                    <option key={status} value={status}>
                      {translateStatus(status)}
                    </option>
                  ))}
                </select>
              </td>
              <td className="p-4">
                <input
                  type="number"
                  min={0}
                  max={100}
                  value={intern.editableMark}
                  onChange={(e) =>
                    handleChange(intern.id, "mark", Number(e.target.value))
                  }
                  className="border rounded px-2 py-1 w-20"
                />
              </td>
              <td className="p-4">
                <select
                  value={intern.editableRecommendation}
                  onChange={(e) =>
                    handleChange(
                      intern.id,
                      "recommendation",
                      e.target.value === "true"
                    )
                  }
                  className="border rounded px-2 py-1"
                >
                  {recommendationOptions.map((option) => (
                    <option key={option.value.toString()} value={option.value}>
                      {option.label}
                    </option>
                  ))}
                </select>
              </td>
              <td className="p-4">
                <button
                  onClick={() => handleSave(intern.id)}
                  disabled={saving}
                  className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 disabled:opacity-50"
                >
                  Save
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

function Enrolls() {
  const [enrolls, setEnrolls] = useState<EnrollResponse[]>([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const loadEnrolls = async () => {
      setLoading(true);
      try {
        const data = await fetchEnrolls();
        setEnrolls(data);
      } catch (error) {
        console.error("Failed to fetch enrolls:", error);
      } finally {
        setLoading(false);
      }
    };

    loadEnrolls();
  }, []);

  const handleAccept = async (email: s, enrollId: number) => {
    try {
      await acceptEnroll(email, enrollId);
      setEnrolls((prev) =>
        prev.map((e) =>
          e.enrollId === enrollId ? { ...e, enrollStatus: "ACCEPTED" } : e
        )
      );
    } catch (error) {
      console.error("Failed to accept enroll:", error);
    }
  };

  const handleReject = async (email: s, enrollId: number) => {
    try {
      await rejectEnroll(email, enrollId);
      setEnrolls((prev) =>
        prev.map((e) =>
          e.enrollId === enrollId ? { ...e, enrollStatus: "REJECTED" } : e
        )
      );
    } catch (error) {
      console.error("Failed to reject enroll:", error);
    }
  };

  if (loading) return <p>Loading enrolls...</p>;

  return (
    <div className="bg-pink-500">
      <h2 className="font-bold text-xl mb-4">Заявки</h2>
      {enrolls.map((e) => (
        <div key={e.enrollId} className="flex items-center gap-10 mb-4">
          <InternProfile fio={e.userInfo.fio} />
          <div className="flex items-center gap-2">
            <Button
              size="sm"
              className="bg-white hover:bg-green-400 text-green-400 hover:text-white border border-green-400 rounded-full"
              onClick={() => handleAccept(e.userInfo.email, e.enrollId)}
              disabled={e.enrollStatus !== "PENDING"}
            >
              Принять
            </Button>
            <Button
              size="sm"
              className="bg-white hover:bg-red-400 text-red-400 hover:text-white border border-red-400 rounded-full"
              onClick={() => handleReject(e.userInfo.email, e.enrollId)}
              disabled={e.enrollStatus !== "PENDING"}
            >
              Отклонить
            </Button>
          </div>
        </div>
      ))}
    </div>
  );
}

interface InternProfileProps {
  fio: s;
}

function InternProfile(p: InternProfileProps) {
  return (
    <div className="flex gap-2 items-center">
      <div
        className={`rounded-xl w-12 h-12 cursor-pointer bg-white`}
        style={{
          backgroundImage: `url()`,
          backgroundSize: "cover",
          backgroundPosition: "center",
        }}
      />
      <p>{p.fio}</p>
    </div>
  );
}

function capitalize(word: s) {
  return word.charAt(0).toUpperCase() + word.slice(1);
}

// Helper function to translate status values to readable text
function translateStatus(status: AnalyticsResponse["status"]): s {
  switch (status) {
    case "IN_PROGRESS":
      return "Проходит";
    case "COMPLETED":
      return "Завершено";
    case "REVIEW":
      return "На рассмотрении";
    case "NOT_STARTED":
      return "Не начато";
    case "REWORK":
      return "На доработке";
    default:
      return status;
  }
}
