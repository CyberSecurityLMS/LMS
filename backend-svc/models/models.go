package models

import (
	"time"
)

type User struct {
	ID             int              `json:"id"`
	Username       string           `json:"username"`
	PasswordHash   string           `json:"-"` // Скрыто в JSON
	Email          string           `json:"email"`
	FullName       string           `json:"fullName"`
	ProfileImage   string           `json:"profileImage,omitempty"`
	TOTPSecret     string           `json:"-"` // Скрыто в JSON
	Is2FAEnabled   bool             `json:"is2faEnabled"`
	IsAdmin        bool             `json:"isAdmin,omitempty"`
	IsActive       bool             `json:"isActive,omitempty"`
	IsTeacher      bool             `json:"isTeacher,omitempty"`
	IsDeleted      bool             `json:"-"` // Скрыто в JSON
	LastLogin      time.Time        `json:"lastLogin,omitempty"`
	Courses        []CourseProgress `json:"courses,omitempty"`
	CompletedTasks int              `json:"completedTasks,omitempty"`
	TotalTasks     int              `json:"totalTasks,omitempty"`
	Progress       float64          `json:"progress,omitempty"`
}

type CourseProgress struct {
	ID                int     `json:"id"`
	VulnerabilityType string  `json:"vulnerabilityType"`
	Description       string  `json:"description"`
	TasksCount        int     `json:"tasksCount"`
	CompletedTasks    int     `json:"completedTasks"`
	Progress          float64 `json:"progress"`
}

type Course struct {
	ID                int    `json:"id"`
	VulnerabilityType string `json:"vulnerabilityType"`
	TasksCount        int    `json:"tasksCount"`
	Description       string `json:"description"`
	Tasks             []Task `json:"tasks"`
}

type Task struct {
	ID          int    `json:"id"`
	CourseID    int    `json:"courseId"`
	Title       string `json:"title"`
	Description string `json:"description"`
	Difficulty  string `json:"difficulty"` // например: "easy", "medium", "hard"
	Order       int    `json:"order"`      // порядковый номер задания в курсе
}

type UserProgress struct {
	UserID    int          `json:"userId"`
	Completed map[int]bool `json:"completed"` // ключ - ID задания
}

type UpdateProfileRequest struct {
	Email    string `json:"email,omitempty"`
	FullName string `json:"fullName,omitempty"`
	Password string `json:"password,omitempty"`
}

type UpdateStatusRequest struct {
	IsActive bool `json:"isActive" binding:"required"`
}

type ChangePasswordRequest struct {
	CurrentPassword string `json:"currentPassword" binding:"required,min=6"`
	NewPassword     string `json:"newPassword" binding:"required,min=6"`
}

type ForgotPasswordRequest struct {
	Email    string `json:"email"`
	Username string `json:"username"`
}

type ResetPasswordRequest struct {
	TempToken   string `json:"tempToken" binding:"required"`
	Code        string `json:"code" binding:"required"`
	NewPassword string `json:"newPassword" binding:"required,min=6"`
}

type LoginRequest struct {
	Username string `json:"username" binding:"required"`
	Password string `json:"password" binding:"required"`
}

type LoginResponse struct {
	Token    string `json:"token"`
	UserID   int    `json:"userId"`
	Username string `json:"username"`
	Email    string `json:"email"`
}

type RegisterRequest struct {
	Username string `json:"username" binding:"required" example:"newuser"`
	Password string `json:"password" binding:"required" example:"newpassword123"`
	Email    string `json:"email" binding:"required" example:"user@example.com"`
	FullName string `json:"fullName" binding:"required" example:"New User"`
}

type RegisterResponse struct {
	Token   string `json:"token"`
	Message string `json:"message"`
}

type VerifyOTPRequest struct {
	TempToken string `json:"tempToken" binding:"required"`
	OTP       string `json:"otp" binding:"required"`
}

type Enable2FARequest struct {
	OTP string `json:"otp" binding:"required" example:"123456"`
}

type ErrorResponse struct {
	Error string `json:"error"`
}

type SuccessResponse struct {
	Message string `json:"message"`
}

type TempTokenResponse struct {
	TempToken string `json:"tempToken"`
	Message   string `json:"message"`
}

type Enable2FAResponse struct {
	Status string `json:"status" example:"2FA enabled"`
}

type DeleteAccountInitRequest struct {
	Password string `json:"password" binding:"required"`
}

type DeleteAccountConfirmRequest struct {
	Code string `json:"code" binding:"required,len=6"`
}
